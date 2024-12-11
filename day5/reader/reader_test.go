package reader_test

import (
	"testing"

	"github.com/ttn-nguyen42/aoc/day5/reader"
	"github.com/ttn-nguyen42/aoc/day5/utils"
)

func TestReader(t *testing.T) {
	t.Run("can read a single rule", func(t *testing.T) {
		r := utils.ReaderFromStrInput("47|53")

		sut := reader.ReadInput(r)

		if len(sut.Rules) != 1 {
			t.Fatalf("expected rules to have 1 item, got %d", len(sut.Rules))
		}

		if len(sut.Updates) != 0 {
			t.Fatalf("expected updates to have 0 item, got %d", len(sut.Updates))
		}

		succ, found := sut.Rules[47]
		if !found {
			t.Fatalf("expected 47 to have a successor")
		}

		if len(succ) != 1 {
			t.Fatalf("expect successor list to have 1 item, got: %d", len(succ))
		}

		if succ[0] != 53 {
			t.Fatalf("first item is not 53, got %d", succ[0])
		}
	})

	t.Run("can read multiple rules", func(t *testing.T) {
		r := utils.ReaderFromStrInput("47|53\n53|59")

		sut := reader.ReadInput(r)

		if len(sut.Rules) != 2 {
			t.Fatalf("expected rules to have 2 items, got %d", len(sut.Rules))
		}

		if len(sut.Updates) != 0 {
			t.Fatalf("expected updates to have 0 item, got %d", len(sut.Updates))
		}

		succ, found := sut.Rules[47]
		if !found {
			t.Fatalf("expected 47 to have a successor")
		}

		if len(succ) != 1 {
			t.Fatalf("expect successor list to have 1 item, got: %d", len(succ))
		}

		if succ[0] != 53 {
			t.Fatalf("first item is not 53, got %d", succ[0])
		}

		succ, found = sut.Rules[53]
		if !found {
			t.Fatalf("expected 53 to have a successor")
		}

		if len(succ) != 1 {
			t.Fatalf("expect successor list to have 1 item, got: %d", len(succ))
		}

		if succ[0] != 59 {
			t.Fatalf("first item is not 59, got %d", succ[0])
		}
	})

	t.Run("can read both rules and updates", func(t *testing.T) {
		r := utils.ReaderFromStrInput(`75|13
53|13

75,47,61,53,29
97,61,53,29,13
`)

		sut := reader.ReadInput(r)

		if len(sut.Rules) != 2 {
			t.Fatalf("expected rules to have 2 items, got %d", len(sut.Rules))
		}

		if len(sut.Updates) != 2 {
			t.Fatalf("expected updates to have 2 items, got %d", len(sut.Updates))
		}

		expectedRules := map[int][]int{
			75: {13},
			53: {13},
		}

		for k, v := range expectedRules {
			succ, found := sut.Rules[k]
			if !found {
				t.Fatalf("expected %d to have a successor", k)
			}

			if len(succ) != len(v) {
				t.Fatalf("expect successor list to have %d item, got: %d", len(v), len(succ))
			}

			for i, s := range succ {
				if s != v[i] {
					t.Fatalf("item %d is not %d, got %d", i, v[i], s)
				}
			}
		}

		expectedUpdates := [][]int{
			{75, 47, 61, 53, 29},
			{97, 61, 53, 29, 13},
		}

		for i, u := range expectedUpdates {
			if len(u) != len(sut.Updates[i]) {
				t.Fatalf("expect update list to have %d item, got: %d", len(u), len(sut.Updates[i]))
			}

			for j, s := range u {
				if s != sut.Updates[i][j] {
					t.Fatalf("item %d is not %d, got %d", j, s, sut.Updates[i][j])
				}
			}
		}

	})
}
