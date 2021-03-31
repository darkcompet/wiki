# Regular Expression (regex)


## Keywords

- `^`: Indicate the string must contain first pattern.

    ```bash
    `^hello`: Matches `hello`, not match `khello`
    ```

- `[^]`: Does NOT start with character following `^`

    ```bash
    `[^hello]`: Matches with `khello`, not match `hello`
    ```