package parser

import (
	"os"
	"strings"
	"testing"

	"github.com/ttn-nguyen42/aoc/day3/reader"
)

func TestParser(t *testing.T) {
	t.Run("can parse single statement", func(t *testing.T) {
		sb := strings.NewReader("mul(480,119)")

		bf, err := reader.NewReader(sb, 1024)
		if err != nil {
			t.Fatalf("failed to initialized reader: %s", err)
		}

		par := Parse(bf)
		if par == 0 {
			t.Fatalf("expected result, got none")
		}

		var expected int64 = 57120
		if par != expected {
			t.Fatalf("expected %d, got %d", expected, par)
		}

	})

	t.Run("can parse multiple statement with noise", func(t *testing.T) {
		sb := strings.NewReader("]select(23,564)/$!where()>%mul(747,16)*why()mul(354,748)how()<?mul(29,805)where()mul(480,119)!,why()mul(685,393)(~'&[wha")

		bf, err := reader.NewReader(sb, 1024)
		if err != nil {
			t.Fatalf("failed to initialize reader: %s", err)
		}

		par := Parse(bf)
		if par == 0 {
			t.Fatalf("expected result, got none")
		}

		var expected int64 = 626414
		if par != expected {
			t.Fatalf("expected %d, got %d", expected, par)
		}
	})

	t.Run("can parse statement with segments", func(t *testing.T) {
		sb := strings.NewReader("]select(23,564)/$!where()>%mul(747,16)*why()mul(354,748)how()<?mul(29,805)where()mul(480,119)!,why()mul(685,393)(~'&[wha")

		bf, err := reader.NewReader(sb, 8)
		if err != nil {
			t.Fatalf("failed to initialize reader: %s", err)
		}

		par := Parse(bf)
		if par == 0 {
			t.Fatalf("expected result, got none")
		}

		var expected int64 = 626414
		if par != expected {
			t.Fatalf("expected %d, got %d", expected, par)
		}
	})

	t.Run("can parse input file", func(t *testing.T) {
		path := "../inputs/input_01.txt"

		f, err := os.Open(path)
		if err != nil {
			t.Fatalf("cannot open file: %s", err)
		}
		defer f.Close()

		bf, err := reader.NewReader(f, 512)
		if err != nil {
			t.Fatalf("failed to initialize reader: %s", err)
		}

		par := Parse(bf)
		if par == 0 {
			t.Fatalf("expected result, got none")
		}

		var expected int64 = 153469856
		if par != expected {
			t.Fatalf("expected %d, got %d", expected, par)
		}

	})

	t.Run("can work across a variety of buffer size", func(t *testing.T) {
		path := "../inputs/input_01.txt"

		f, err := os.Open(path)
		if err != nil {
			t.Fatalf("cannot open file: %s", err)
		}
		defer f.Close()

		fileInfo, _ := os.Stat(path)
		fileSize := fileInfo.Size()

		for i := 1; i < int(2*fileSize); i = i * 2 {
			bf, err := reader.NewReader(f, int64(i))
			if err != nil {
				t.Fatalf("failed to initialize reader: %s", err)
			}

			par := Parse(bf)
			if par == 0 {
				t.Fatalf("expected result, got none")
			}

			var expected int64 = 153469856
			if par != expected {
				t.Fatalf("expected %d, got %d at buffer size: %d", expected, par, i)
			}

			f.Seek(0, 0)
		}
	})
}
