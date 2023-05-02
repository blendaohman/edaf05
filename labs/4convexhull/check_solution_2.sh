#!/bin/bash
# chmod +x check_solution_2.sh
# ./check_solution_2.sh java solution

for x in $(ls data | sort -n); do
    for y in data/$x/*.in; do
        pre=${y%.in}
        out=$pre.out
        correct=$pre.correct
        if diff <($* <$y | sort -k2,2 -k1) <(sort -k2,2 -k1 $correct) >/dev/null; then
            echo passed $y
        else
            echo failed for $y
            diff <($* <$y | sort -k2,2 -k1) <(sort -k2,2 -k1 $correct)
            exit 1
        fi
    done
done