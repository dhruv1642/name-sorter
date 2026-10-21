# Name Sorter

![build](https://github.com/dhruv1642/name-sorter/actions/workflows/build.yml/badge.svg)

A small command-line tool that sorts a list of names. It sorts by last name first, and if two people share a last name it looks at their given names in order.

Each name needs 1 to 3 given names followed by a last name, for example `Hunter Uriah Mathew Clarke`.

## What you need

- Java 17 or newer
- Maven 3.8 or newer

## Build and test

```
mvn verify
```

This compiles the code, runs the tests and builds `target/name-sorter.jar`.

## Run it

```
java -jar target/name-sorter.jar ./unsorted-names-list.txt
```

The sorted names are printed to the screen and also saved to `sorted-names-list.txt` in the folder you ran the command from. If that file already exists it gets overwritten.

There is a sample input in the repo (`unsorted-names-list.txt`) if you want to try it straight away.

## How names are sorted

- The last word on a line is the last name. Everything before it is a given name.
- Names are compared by last name, then first given name, then second, then third.
- Upper and lower case are treated the same, so `adam smith` and `Adam Smith` count as equal.
- If one name is just a shorter version of another, the shorter one goes first. `Ann Lee` comes before `Ann Marie Lee`.
- Blank lines in the input are skipped.
- If a line has no given name or more than three, the program stops with an error message and writes nothing.

## How the code is build:

The code is split so each part has one job and can be changed without touching the others.

- `domain` holds the actual logic: `Name`, `NameParser`, `NameComparator` and `NameSorter`.
- `io` handles reading and writing. `NameSource` and `NameSink` are small interfaces, with implementations for files and the console.
- `app` has `NameSorterApp`, which ties it together: read, parse, sort, write.
- `Main` reads the command-line argument and connects everything.

`NameSorterApp` only knows about the two interfaces, so it doesn't care where names come from or where they go. That also makes it easy to test without touching real files. If the sorting rules ever change, only `NameComparator` needs to be edited.

## Tests

Unit tests are under `src/test`. They cover parsing, the sorting rules (including ties and case), the file and console input and output, and the error cases.

## Build pipeline

A GitHub Actions workflow (`.github/workflows/build.yml`) runs `mvn verify` on every push and pull request.