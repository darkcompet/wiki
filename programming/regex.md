# Regular Expression

Regular Expression (regex) to handle string pattern.

---

## Special symbols

- `\`: place before a special character to remove its specialness. And place before a
non-special character to enable it to Special Word Boundary Character.
Eg, `\b` means boundary, `\*` means normal star symbol.

- `()`: grouping characters. Eg, `(abc)+` matches `abcabcabcabc`.

- `^`: start with the first character in pattern. Eg, `^fir` matches `first`, but not match `xfirst`.

- `[^abc]`: complemented character set. It matches any character except following characters.
Eg, `[^ab]` matches `x`, but not match `a` or `b`.

- `$`: end with the last character in pattern. Eg, `en$` matches `then`, but not match `end`.

- `*`: match zero or more of the preceding character. Eg, `a*b` matches `b, ab, aab,...`.

- `+`: match more (at least one) of the preceding character. Eg, `a+b` matches `ab, aab, aaab,...`.

- `?`: match zero or one of the preceding character. Eg, `colou?r` matches both `color` and `colour`.

- `\0`: null character.

- `\b`: matches a word boundary that is not preceded or followed by another word-character.
Eg, `\bxyz` matches `xyz` in `xyzt`, but `\bxyz\b` does not match anything in `xyzt`.

- `[\b]`: backspace character.

- `\d`: a digit from 0 to 9. Equiv to `[0-9]`.

- `\D`: not a digit. Equiv to `[^0-9]`.

- `\f`: form-feed.

- `\n`: newline.

- `\r`: return.

- `\s`: single whitespace character, including tab, space, form feed, line feed.

- `\S`: not white space.

- `\t`: tab character.

- `\w`: any alphanumeric character including underscore. Equiv to `[a-zA-Z0-9_]`.

- `\W`: non-word character. Equiv to `[^a-zA-Z0-9_]`.

- Quantities:

   - `{N}`: exact N times of preceding string. Eg: `\w{3}` matches with `aka`, not with `akah` or `ak`.
   - `{M, N}`: from M to N times of preceding string. Eg: `\w{2,4}` matches with `ab`, not with `a` or `abcde`.
   - `{N,}`: at least N times of preceding string. Eg: `\d{2,}` matched with `12`, not with `1`.
