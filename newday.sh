#!/bin/bash

day=0
year=$(date +%Y)

if [ $# -ne 1 ]; then
  echo "You forgot the day, my dude."
  exit 1;
else
  if [ "$1" -lt 10 ]; then
    day="0$1"
  else
    day=$1
  fi
  echo "I'm generating the code for day $day, year $year"
fi

inputDir="in/_$year";
if [ ! -d "$inputDir" ]; then
  echo "$inputDir does not exist. Creating..."
  mkdir $inputDir
fi

puzzleTestInput="in/_$year/puzzle$day.test.in"
puzzleInput="in/_$year/puzzle$day.in"

touch "$puzzleInput" "$puzzleTestInput"

puzzleJava="Puzzle${day}.java"
puzzleTestJava="Puzzle${day}Test.java"

cp templates/PuzzleTemplate.java "$puzzleJava"
cp templates/PuzzleTestTemplate.java "$puzzleTestJava"

sed -i .trash "s/__DAY__/$day/g" "$puzzleJava"
sed -i .trash "s/#YEAR#/$year/g" "$puzzleJava"

sed -i .trash "s/__DAY__/$day/g" "$puzzleTestJava"
sed -i .trash "s/#YEAR#/$year/g" "$puzzleTestJava"

srcDir="src/main/java/com/ionutciuta/puzzles/_$year";
if [ ! -d "$srcDir" ]; then
  echo "$srcDir does not exist. Creating..."
  mkdir "$srcDir"
fi

mv "$puzzleJava" "$srcDir"

testDir="src/test/java/com/ionutciuta/puzzles/_$year";
if [ ! -d "$testDir" ]; then
  echo "$testDir does not exist. Creating..."
  mkdir "$testDir"
fi

mv "$puzzleTestJava" "$testDir"

rm *.trash

git add "$puzzleInput" \
  "$puzzleTestInput" \
  "$srcDir/$puzzleJava" \
  "$testDir/$puzzleTestJava"

echo "Done!"