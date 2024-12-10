package reader

import (
	"errors"
	"fmt"
	"io"
	"sync"
)

type Reader struct {
	mu sync.Mutex

	bufSize int64
	buf     []byte
	src     io.Reader
	isDone  bool

	i int64
}

func NewReader(src io.Reader, bufSize int64) (*Reader, error) {
	r := &Reader{
		src:     src,
		i:       0,
		bufSize: bufSize,
		isDone:  false,
	}

	if r.src == nil {
		return nil, fmt.Errorf("src is nil")
	}

	if r.bufSize <= 0 {
		return nil, fmt.Errorf("buffer size is 0")
	}

	r.buf = make([]byte, r.bufSize)

	return r, nil
}

func (r *Reader) Read() (n int, err error) {
	r.mu.Lock()
	defer r.mu.Unlock()

	if len(r.buf) != int(r.bufSize) {
		panic(len(r.buf))
	}
	n, err = r.src.Read(r.buf)
	if err != nil {
		if errors.Is(err, io.EOF) {
			r.isDone = true
			r.i += int64(n)
		}
		return n, err
	}

	r.i += int64(n)
	return n, nil
}

func (r *Reader) String() string {
	r.mu.Lock()
	defer r.mu.Unlock()

	res := string(r.buf)
	clear(r.buf)

	return res
}

func (r *Reader) Bytes() []byte {
	r.mu.Lock()
	defer r.mu.Unlock()

	return r.buf
}

func (r *Reader) Done() bool {
	r.mu.Lock()
	defer r.mu.Unlock()

	return r.isDone
}
