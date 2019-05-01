# JMORE FrameWork

```text
         _   .-')                _  .-')     ('-.
        ( '.( OO )_             ( \( -O )  _(  OO)       ,-.,-.,-.
     ,--.,--.   ,--.).-'),-----. ,------. (,------.       \ \\ \\ \
 .-')| ,||   `.'   |( OO'  .-.  '|   /`. ' |  .---'        \ \\ \\ \
( OO |(_||         |/   |  | |  ||  /  | | |  |             \ \\ \\ \
| `-'|  ||  |'.'|  |\_) |  |\|  ||  |_.' |(|  '--.          / // // /
,--. |  ||  |   |  |  \ |  | |  ||  .  '.' |  .--'         / // // /
|  '-'  /|  |   |  |   `'  '-'  '|  |\  \  |  `---.       / // // /
 `-----' `--'   `--'     `-----' `--' '--' `------'      `-'`-'`-'
```

## API JSON SCHEMA

All communications with backend service is based on json format.

### Success Body

```json
{
  "code": 0,
  "timestamp": 1524640724522,
  "msg": "Success",
  "data": ["label"]
}
```

```json
{
  "code": 0,
  "timestamp": 1524640724522,
  "msg": "Success",
  "data": {
    "name": "jmore"
  }
}
```

### Failure Body

```json
{
  "code": -1010,
  "timestamp": 1524640724522,
  "msg": "Failure",
  "data": null
}
```
