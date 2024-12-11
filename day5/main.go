package main

import (
	"flag"
	"log"
	"os"
	"path/filepath"

	"github.com/ttn-nguyen42/aoc/day5/reader"
	"github.com/ttn-nguyen42/aoc/day5/updater"
)

func main() {
	inputPtr := flag.String("input", "input.txt", "path to input file")
	flag.Parse()

	if inputPtr == nil {
		panic("input file is required")
	}

	abs, err := filepath.Abs(*inputPtr)
	if err != nil {
		log.Fatalf("failed to get absolute path: %s", err)
	}

	stats, err := os.Stat(abs)
	if err != nil {
		log.Fatalf("input file does not exists: %s", *inputPtr)
	}

	if stats.IsDir() {
		log.Fatalf("input file is a directory")
	}

	f, err := os.Open(abs)
	if err != nil {
		log.Fatalf("failed to open file: %s", err)
	}
	defer f.Close()

	input := reader.ReadInput(f)

	updates := updater.GetOrederedUpdates(input)

	totalMid := updater.Reduce(updates, func(acc int, upd []int) int {
		mid := len(upd) / 2

		return acc + upd[mid]
	})

	log.Printf("total mid: %d", totalMid)

	updates = updater.FixUnordered(input)

	totalMid = updater.Reduce(updates, func(acc int, upd []int) int {
		mid := len(upd) / 2

		return acc + upd[mid]
	})

	log.Printf("total mid after sort: %d", totalMid)
}
