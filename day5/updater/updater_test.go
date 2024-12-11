package updater_test

import (
	"testing"

	"github.com/ttn-nguyen42/aoc/day5/reader"
	"github.com/ttn-nguyen42/aoc/day5/updater"
	"github.com/ttn-nguyen42/aoc/day5/utils"
)

func TestUpdater(t *testing.T) {

	var orderedUpdates [][]int

	t.Run("can get ordered updates", func(t *testing.T) {
		src := utils.ReaderFromStrInput(`47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47`)

		rd := reader.ReadInput(src)

		sutResult := updater.GetOrederedUpdates(rd)

		expected := [][]int{
			{75, 47, 61, 53, 29},
			{97, 61, 53, 29, 13},
			{75, 29, 13},
		}

		if len(sutResult) != len(expected) {
			t.Fatalf("expected %d updates, got %d", len(expected), len(sutResult))
		}

		for i, upd := range sutResult {
			if len(upd) != len(expected[i]) {
				t.Fatalf("expected update %d to have %d items, got %d", i, len(expected[i]), len(upd))
			}

			for j, item := range upd {
				if item != expected[i][j] {
					t.Fatalf("expected update %d to have %d at position %d, got %d", i, expected[i][j], j, item)
				}
			}
		}

		orderedUpdates = sutResult
	})

	t.Run("can reduce updates", func(t *testing.T) {
		if orderedUpdates == nil {
			t.Skip()
		}

		sutRes := updater.Reduce(orderedUpdates, func(acc int, upd []int) int {

			mid := len(upd) / 2
			acc += upd[mid]

			return acc
		})

		expected := 143

		if sutRes != expected {
			t.Fatalf("expected %d, got %d", expected, sutRes)
		}
	})
}
