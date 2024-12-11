package updater

import (
	"log"
	"slices"

	"github.com/ttn-nguyen42/aoc/day5/reader"
)

func GetOrederedUpdates(inp *reader.Input) (updates [][]int) {
	updates = make([][]int, 0)

	for _, upd := range inp.Updates {
		if isOrdered(upd, inp.Rules) {
			updates = append(updates, upd)
		}
	}

	return
}

func GetUnorderedUpdates(inp *reader.Input) (updates [][]int) {
	updates = make([][]int, 0)

	for _, upd := range inp.Updates {
		if !isOrdered(upd, inp.Rules) {
			updates = append(updates, upd)
		}
	}

	return
}

func isOrdered(upd []int, rules map[int][]int) bool {
	for i := 0; i < len(upd); i += 1 {
		arg := upd[i]

		afters := rules[arg]

		for j := i - 1; j >= 0; j -= 1 {
			nextArg := upd[j]

			found := false

			for _, succ := range afters {
				if succ == nextArg {
					found = true

					break
				}
			}

			if found {
				return false
			}
		}
	}

	return true
}

func Reduce(updates [][]int, reducer func(int, []int) int) int {
	total := 0

	for _, upd := range updates {
		total = reducer(total, upd)
	}

	return total
}

func FixUnordered(inp *reader.Input) (unordered [][]int) {
	unordered = GetUnorderedUpdates(inp)

	for i, upd := range unordered {
		sortByRule(upd, inp.Rules)

		unordered[i] = upd
	}

	if true {
		for _, ord := range unordered {
			if !isOrdered(ord, inp.Rules) {
				log.Printf("is not ordered yet: %v", ord)
			}
		}
	}

	return
}

func sortByRule(upd []int, rules map[int][]int) {
	slices.SortStableFunc(upd, func(first int, second int) int {
		validSuccessors := rules[first]

		for _, val := range validSuccessors {
			if second == val {
				return -1
			}
		}

		return 1
	})
}
