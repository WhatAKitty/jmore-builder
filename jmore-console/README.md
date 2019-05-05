# JMore Console Module

Develop based on JMore Framework, do great job on non web environment.

## 功能

- [x] Do response while user type something.
- [x] Support command history
- [x] Support command undo
- [x] Support aop of command execution
- [x] Support batch command creation and execution
- [ ] Support calling of outer gui app or shell command
- [ ] Support analyze of some human language

## Guide Mind

Guide Mind：DDD

### Main Goal

* Support execution of command

### User Demand

* The user type command, the shell passed the command into executor and response to the user
* The user can look up the history of commands and be able to rollback to certain command, previous of all commands will be rollback.
* The user can package some commands and execute it forever
* The command has life cycle that hook of before, after, failure, success and finish

### Bounded Context

![Bounded Context](https://static.xuqiang.me/public/images/%E6%88%98%E7%95%A5%E8%AE%BE%E8%AE%A1%E2%80%94%E2%80%94%E9%99%90%E7%95%8C%E4%B8%8A%E4%B8%8B%E6%96%87-1.png)

### Context Mapping

![Context Mapping](https://static.xuqiang.me/public/images/%E6%88%98%E7%95%A5%E8%AE%BE%E8%AE%A1%E2%80%94%E2%80%94%E4%B8%8A%E4%B8%8B%E6%96%87%E6%98%A0%E5%B0%84%E5%9B%BE-1.png)

### Aggregate Root

* Command
* History
* Context

### Entity

This will list all entity of [Command Sub Domain], [History Sub Domain] and [Context Support Sub Domain], tagged it while it is value object

#### Command Sub Domain

* Command
* Receiver【Value Object】
* BatchCommand
* Domain Service——Command Parser【Transform the human language into the command and receiver】

#### History Sub Domain

* History
* Command Snapshot【Value Object】
* Rollback Script【Value Object】

### Context Supprot Sub Domain

* Context

### Domain Model

* One command has one receiver
* BatchCommand is the collection of single command

![Domain Model](https://static.xuqiang.me/public/images/Shell%E6%8E%A7%E5%88%B6%E5%8F%B0DDD%E8%AE%BE%E8%AE%A1-2.png)

