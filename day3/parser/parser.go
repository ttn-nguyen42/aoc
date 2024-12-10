package parser

import (
	"fmt"

	"strconv"

	"github.com/ttn-nguyen42/aoc/day3/reader"
)

func Parse(r *reader.Reader) int64 {
	var total int64 = 0

	cur := ""
	enabled := true

	for !r.Done() {
		r.Read()
		cur += r.String()

		i := 0
		for {
			if i >= len(cur) {
				cur = ""
				break
			}

			ch := cur[i]
			if ch == 'm' {
				result, end, fetchMore := parseMul(cur, i)
				if result != -1 {
					if enabled {
						total += result
					}
				}
				i = end
				if fetchMore {
					cur = cur[end:]
					break
				}
			} else if ch == 'd' {
				result, end, fetchMore := parseToggler(cur, i)
				switch result {
				case 1:
					enabled = true
				case 0:
					enabled = false
				}

				i = end
				if fetchMore {
					cur = cur[end:]
					break
				}
			} else {
				i += 1
			}
		}
	}

	return total
}

func parseMul(str string, i int) (result int64, end int, fetchMore bool) {
	endOper := i + 3
	if endOper >= len(str) {
		// reset the cursor to original place
		// tell the user to fetch more data
		return -1, i, true
	}

	oper := str[i:endOper]
	if string(oper) != "mul" {
		// move the cursor to after m
		return -1, i + 1, false
	}

	startArgs := endOper + 1
	if startArgs >= len(str) {
		// reset the cursor to original place
		// tell the user to fetch more data
		return -1, i, true
	}

	if str[startArgs-1] != '(' {
		// move the cursor to first after 'mul'
		return -1, startArgs, false
	}

	firstArgBytes := make([]byte, 0, 3)
	firstArgEnd := -1

	for idx := startArgs; idx < startArgs+5; idx += 1 {
		if idx >= len(str) {
			// not enough data to proceed, move the cursor to the original place
			return -1, i, true
		}
		// find ','
		if str[idx] == ',' {
			firstArgEnd = idx
			break
		}

		firstArgBytes = append(firstArgBytes, str[idx])
	}

	if firstArgEnd == -1 {
		// reset cursor to after '('
		return -1, startArgs + 1, false
	}

	firstArg, err := strconv.Atoi(string(firstArgBytes))
	if err != nil {
		// restart cursor to after '('
		return -1, startArgs + 1, false
	}

	secondArgBytes := make([]byte, 0, 3)
	secondArgEnd := -1

	for idx := firstArgEnd + 1; idx < firstArgEnd+5; idx += 1 {
		if idx >= len(str) {
			// not enough data to proceed, move the cursor to the original place
			return -1, i, true
		}
		// find ','
		if str[idx] == ')' {
			secondArgEnd = idx
			break
		}

		secondArgBytes = append(secondArgBytes, str[idx])
	}

	if secondArgEnd == -1 {
		// reset cursor to after ','
		return -1, firstArgEnd + 1, false
	}

	secondArg, err := strconv.Atoi(string(secondArgBytes))
	if err != nil {
		// reset cursor to after ','
		return -1, firstArgEnd + 1, false
	}

	if firstArg > 999 || secondArg > 999 {
		panic(fmt.Sprintf("something is wrong, argument is not 3 bytes: %d and %d", firstArg, secondArg))
	}

	result = int64(firstArg * secondArg)

	return result, secondArgEnd + 1, false
}

func parseToggler(str string, i int) (result int, end int, fetchMore bool) {
	c := i + 4
	if c > len(str) {
		return -1, i, true
	}

	ins := string(str[i:c])
	if ins == "do()" {
		return 1, c, false
	}

	if ins != "don'" {
		// optimization: we wouldn't want to allocate a string of 5 bytes ahead everytime encounter a 'd'
		// for example: "ddddddddddddd"
		idx := 0
		for idx = i + 1; idx < len(str); idx += 1 {
			if str[idx] != 'd' {
				break
			}
		}

		return -1, idx, false
	}

	// TODO: I should have written a peek(n int) function. Bruh that's compiler 101 tho.
	endLastPeek := c

	c = c + 3
	if c > len(str) {
		return -1, i, true
	}

	ins = string(str[endLastPeek:c])
	if ins != "t()" {
		// optimization: we wouldn't want to allocate a string of 5 bytes ahead everytime encounter a 'd'
		// for example: "ddddddddddddd"
		idx := 0
		for idx = i + 1; idx < len(str); idx += 1 {
			if str[idx] != 'd' {
				break
			}
		}

		return -1, idx, false
	}

	return 0, c, false
}
