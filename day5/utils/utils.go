package utils

import (
	"bytes"
	"io"
	"os"
	"testing"
)

func ReaderFromStrInput(str string) io.Reader {
	return bytes.NewBufferString(str)
}

func ReaderFromFileInput(t *testing.T, path string) (io.Reader, func() error) {
	stats, err := os.Stat(path)
	if err != nil {
		t.Fatalf("input file does not exists: %s", path)
	}

	if stats.IsDir() {
		t.Fatalf("input file is a directory")
	}

	f, err := os.Open(stats.Name())
	if err != nil {
		t.Fatalf("failed to open file: %s", err)
	}

	return f, f.Close
}
