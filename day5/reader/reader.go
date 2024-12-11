package reader

import (
	"bufio"
	"io"
	"log"
	"strconv"
	"strings"
)

type Input struct {
	Rules   map[int][]int
	Updates [][]int
}

func ReadInput(in io.Reader) *Input {
	inp := &Input{}

	sc := bufio.NewScanner(in)

	readRules(sc, inp)
	readUpdates(sc, inp)

	return inp
}

func readRules(sc *bufio.Scanner, inp *Input) {
	inp.Rules = make(map[int][]int)

	for sc.Scan() {
		line := sc.Text()

		if len(line) == 0 {
			break
		}

		args := strings.Split(line, "|")
		if len(args) != 2 {
			return
		}

		pre, err := strconv.Atoi(args[0])
		if err != nil {
			log.Fatalf("invalid rule: %s", line)
		}

		aft, err := strconv.Atoi(args[1])
		if err != nil {
			log.Fatalf("invalid rule: %s", line)
		}

		list, exists := inp.Rules[pre]
		if !exists {
			list = make([]int, 0)
		}

		inp.Rules[pre] = append(list, aft)
	}
}

func readUpdates(sc *bufio.Scanner, inp *Input) {
	inp.Updates = make([][]int, 0)

	for sc.Scan() {
		line := sc.Text()

		if len(line) == 0 {
			break
		}

		args := strings.Split(line, ",")
		if len(args) == 0 {
			return
		}

		update := make([]int, 0, len(args))

		for _, arg := range args {
			val, err := strconv.Atoi(arg)
			if err != nil {
				log.Fatalf("invalid update: %s", line)
			}
			update = append(update, val)
		}

		inp.Updates = append(inp.Updates, update)

	}
}
