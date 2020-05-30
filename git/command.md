# Git Command Line List


## ssh-keygen

- To work with git via ssh connection, we need register a ssh key with git server.
Note that, saved file name is important since Git will check it to validate with server
registered ssh key.

   ```bash
      # with ed25519:
      ssh-keygen -t ed25519 -C "darkcompet@gmail.com"
      # or with rsa:
      ssh-keygen -t rsa -b 4096 -C "darkcompet@gmail.com"
   ```


## git add

- Add all records to index-tree

   ```bash
   git add --all
   ```

- Force add git-ignored file/dir to git

   ```bash
   git add -f [path_to_file_or_dir]
   ```


## git branch

- List branch in remote

   ```bash
   # list branch in local
   git branch

   # list all remote branches
   git branch -a
   ```

- Create new branch

   ```bash
   git branch [new_branch_name]
   ```

- Delete a branch

      ```bash
      # delete local branch iff it was pushed and merged
      git branch -d [local_branch_name]

      # delete local branch forcely
      git branch -D [local_branch_name]
      
      # delete remote branch
      git push origin --delete [remote_branch_name]
      ```


## git checkout

- Switch branch

   ```bash
      # Switch local branch to another branch
      git checkout [branch_name]
   ```

- Discard unstaged changes

   ```bash
   # this will discard UNSTAGED changes to last commited version or last saved in staging area
   git checkout -- [file_path]

   # OR discard all changes in files
   git checkout -- .

   # get a file from local commit to workspace. Note that this will replace current file or add new
   git checkout [commit_hash] -- [file_path]

   # get a file from local master to workspace. Note that this will replace current file or add new
   git checkout origin/master -- [file_path]
   ```

- Create (clone) new local branch from a branch

   ```bash
   git checkout -b [new_branch_name] [src_branch_name]

   # or checkout branch first then create new local branch
   git checkout [src_branch_name]
   git checkout -b [new_branch_name]
   ```


## git clone

- Clone remote repo to local repo.

   ```bash
      # Normally clone
      git clone https://gitlab.com/darkcompet/flutter_core.git

      # Clone with username
      git clone https://darkcompet@gitlab.com/darkcompet/flutter_core.git
   ```


## git commit

 - Commit workspace changes in staging area to local repo.

   ```bash
      # Commit workspace changes to local repo
      git commit -m "[commit_message]"
   ```

- Change lastest commited message

   ```bash
   git commit --amend -m "[new_commit_message]"
   ```


## git config

- View config of `system, global and local` git.

   ```bash
      # show all config of git everywhere
      git config --list --show-origin

      # Set Global account (config file at ~/.gitconfig)
      git config --global user.email "darkcompet@gmail.com"
      git config --global user.name "darkcompet"

      # Get Glocal account
      git config --global --get user.email
      git config --global --get user.name

      # Set Local account
      git config user.email "darkcompet@gmail.com"
      git config user.name "darkcompet"

      # Get Local account
      git config --get user.email
      git config --get user.name

      # Clear current user to push with other user
      git config --local credential.helper ""

      # Set editor
      git config --global core.editor nano
      # on Windows, we need specify path to the editor
       git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"
   ```

- Unset password which be stored in credential. It is useful if you has changed password in gitlab.

   ```bash
      # unset password which be stored in credential
      git config --system --unset credential.helper
   ```

## git cherry-pick

>see-also: merge, rebase

>git cherry-pick --continue
>git cherry-pick --abort
>git cherry-pick --quit

Unlike `merge`, this command will not create commit after merging.

- Merge some commit from another branch to a branch

   ```bash
   # merge a commit from master to feature
   git checkout [feature_branch_name]
   git cherry-pick [master_some_commit_id]
   ```

   ※ It is `not practical`, but if we wanna merge range of commits to a branch, just declares them

   ```bash
   git cherry-pick [master_commit_id_1] [master_commit_id_3] [master_commit_id_7]
   ```


## git diff

By default, `git diff` shows only changes between `unstaged files` with `repo`, it we wanna target to
`staged files`, we just add `--staged` as an option.

- Show diff of unstaged files

   ```bash
   git diff
   ```

- Show diff of staged files

   ```bash
   # we can use --cached instead of --staged since they are synonyms
   git diff --staged
   ```


## git fetch

- Fetch repos and refs from remote

   ```bash
   git fetch
   ```


## git flow

- Init git flow for a project

   ```bash
   git flow init
   ```


## git log

- See log of all commits.

   ```bash
      # see only commit log
      git log

      # see with filename
      git log --name-only
   ```


## git push

- Publish a local branch to remote (push a local branch to remote at first time)

   ```bash
   git push -u origin [branch_name]
   ```

- Update local repo to remote repo after you have published local branch.

   ```bash
      # push current banch to remote branch
      git push

      # push to a branch when you are in any branch
      git push origin [branch_name]
   ```


## git merge

>see-also: rebase, cherry-pick

This command will merge a `changes of last commit` of a branch to another branch.

- Merge a branch to another branch.

   ```bash
      # Merge feature branch to master branch at local
      git checkout master
      git merge [feature_branch_name]

      # when merging is fast-forward, we can customize commit message by specify --no-ff argument
      git merge --no-ff develop
   ```

- Merge `last snapshot` to another branch. Note that, this will combine all commits of a branch,
then commit them as last snapshot to another branch.

   ```bash
   # only merge commit will be in git log history (not all commits of feature branch)
   git checkout master
   git merge --squash [feature_branch_name]
   ```

- Merging strategy list ([see merge-strategy](https://www.atlassian.com/git/tutorials/using-branches/merge-strategy))

   - `recursive`: This operates on two heads. Recursive is the default merge strategy when pulling or merging one branch. Additionally this can detect and handle merges involving renames, but currently cannot make use of detected copies. This is the default merge strategy when pulling or merging one branch.

      ```bash
      git merge -s recursive [branch1_name] [branch2_name]
      ```

   - `resolve`: This can only resolve two heads using a 3-way merge algorithm. It tries to carefully detect cris-cross merge ambiguities and is considered generally safe and fast.

      ```bash
      git merge -s resolve [branch1_name] [branch2_name]
      ```

   - `octopus`: The default merge strategy for more than two heads. When more than one branch is passed octopus is automatically engaged. If a merge has conflicts that need manual resolution octopus will refuse the merge attempt. It is primarily used for bundling similar feature branch heads together.

      ```bash
      git merge -s octopus [branch1_name] [branch2_name] [branch3_name] [branchN_name]
      ```

   - `ours`: The Ours strategy operates on multiple N number of branches. The output merge result is always that of the current branch HEAD. The "ours" term implies the preference effectively ignoring all changes from all other branches. It is intended to be used to combine history of similar feature branches.

      ```bash
      git merge -s octopus [branch1_name] [branch2_name] [branch3_name] [branchN_name]
      ```

   - `subtree`: This is an extension of the recursive strategy. When merging A and B, if B is a child subtree of A, B is first updated to reflect the tree structure of A, This update is also done to the common ancestor tree that is shared between A and B.

      ```bash
      git merge -s subtree [branch1_name] [branch2_name]
      ```


## git rebase

>see-also: merge, cherry=pick

>options: --continue, --skip, abort

Like `merge` command, this will merge without creating `merge-commit`.

- Update changes from master to current feature branch

   ```bash
   git checkout [feature_branch_name]
   git rebase master
   ```

   For example:

   ```
                A1---B1---C1 feature-1
            /
   A---B---C---D---E---F---G master

   ↓ (rebase done)

                                A1---B1---C1 feature-1
                            /
   A---B---C---D---E---F---G master
   ```


## git reset

>See more: git reset --help

The command `git reset` will reset to target commit. In general, it has general 3 options: `--soft, --mixed, --hard`,
but restrictly speaking it has more than.


- `git reset --soft`: Reset `HEAD` only.

   ```bash
   # only change pointer of HEAD to target commit
   # staging index or workspace will be not changed
   git reset --soft [commit_id]
   ```

   ※ Note about usecase of this option: we can use this to change commit message, rebase a commit...

- `git reset --mixed`: This is default option, will reset `HEAD, staging index`.

   ```bash
   # point to target commit and reset staging index.
   git reset --mixed [commit_id]
   ```

- `git reset --hard`: Reset `HEAD, staging index, workspace`.

   ```bash
      # switch current branch to a commit. Note that, this will reset everything in local
      # (workspace, staging, local repo) to make current branch same with target commit.
      git reset --hard [commit_id]
   ```

- Delete lastest commited 

   ```bash
   # keeping our changes
   git reset --soft HEAD~1

   # delete workspace changes, staing and commit (require: not yet push)
   git reset --hard HEAD~
   # or
   git reset --hard HEAD~1
   ```

- Unstage a staged file

   ```bash
   # reset the file with content in current repo which be pointed by HEAD (our pointer)
   git reset HEAD [file_name]
   ```


## git remote

- Show all info on remote

   ```bash
   git remote show origin
   ```


## git rm

Remove index of file from staging area. Note that, this command will `physically delete` that file
from current repo after we pull.

- Remove cache (all indices) from staging area. It is useful if we forgot commit unwanted files to git,
and then wanna `remove that file from git` but `keep them in our workspace` (don't forget add them to `.gitignore`
to ignore them from untracked files).

   ```bash
   # remove all indices from staging area
   git rm -r --cached .
   
   # use -f to force remove all indices from staging area
   git rm -r -f --cached .

   # remove all log files under logs/ (don't forget add \ before * to make it effect)
   git rm --cached logs/\*.log
   ```


## git status

- Check state of the files.

   ```bash
      # See state of the files as tracked, modified or stagged...
      git status
   ```


## git stash

- Move current changes in workspace to temporary space. This will compute changes between workspace
with last commit in local repo.

   ```bash
   git stash
   ```

- Apply last stashed changes to workspace

   ```bash
   git stash pop
   ```

- Apply all changes in stash to workspace

   ```bash
   git stash apply
   ```

- Remove last stashed changes from stash-space

   ```bash
   git stash drop
   ```

- Remove all changes in stash-space

   ```bash
   git stash clear
   ```


## git submodule

Allow add sub-git in a git-project.

- Init submodule

   ```bash
   # goto project and add sub-git as submodule
   git submodule add [our_subgit_https://github.com/username/demo.git]
   ```

- Push/Pull submodule

   ```bash
   # change something in submodule folder and push
   cd [sub-git-folder]
   nano ....
   git commit -m "edit submodule"
   git push origin master

   # pull submodule at other dev
   git submodule update
   ```



## git tag

Tag specific point to a repo. It is useful for marking release points.

- List all tag

   ```bash
   # list all tag
   git tag

   # list tags with pattern
   git tag --list "1.18.*4"
   ```


## git update-index

This command works with staging area.

- Tell git ignores a file, so any changes of that file in workspace will be ignored.

   ```bash
   git update-index --skip-worktree [file_path]
   ```

- Tell git does not ignore a file in workspace

   ```bash
   git update-index --no-skip-worktree [file_path]
   ```

- Tell git ignore a file

   ```bash
   git update-index --assume-unchanged [file_path]
   ```

- Tell git un-ignore a file

   ```bash
   git update-index --no-assume-unchanged [file_path]
   ```
