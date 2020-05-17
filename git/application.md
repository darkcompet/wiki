# Application


## Sync local repository with remote repository

- Edit, Commit then Push local changes to remote repo.

   ```bash
      git add --all
      git commit -m "commit_message"
      git push
   ```

   Or we can shortly type with

   ```bash
   git commit -am "commit_message"
   git push
   ```


## Git flow

This will imitate `git-flow` by combining git commands.

#### Hotfix feature

- Create, Release a hotfix branch.

   ```bash
   # create new hotfix branch form master branch
   git checkout master
   git branch -b [hotfix_branch_name]

   # assume we edit something...

   # merge hotfix to master
   git checkout master
   git merge [hotfix_branch_name]

   # merge hotfix to develop
   git checkout develop
   git merge [hotfix_branch_name]
   # or generate commit message
   git merge --no-ff [hotfix_branch_name]

   # delete hotfix branch
   git branch -D [hotfix_branch_name]
   ```

#### Feature branch

>https://makandracards.com/makandra/36003-recommended-git-workflow-for-feature-branches
>https://softwareengineering.stackexchange.com/questions/351727/working-on-a-branch-with-a-dependence-on-another-branch-that-is-being-reviewed

- When develop long-live feature branch, we need update current branch with master everyday.

   ```bash
   # this is equivalent to: git checkout master; git fetch
   git fetch origin master:master
   git rebase master
   ```


## Staging area

Effect `.gitignore` file

- This will remove all index from staging area. So we can re-add files from workspace.

   ```bash
      git rm -r --cached .
   ```


## Discard changes

>https://docs.gitlab.com/ee/topics/git/numerous_undo_possibilities_in_git/
>https://www.earthdatascience.org/workshops/intro-version-control-git/undoing-things/

- Before staged (before run `git add`), we can discard changes which be made in workspace.

   ```bash
      # discard to last staged or last commited
      git checkout -- [file_path]
      # or discard all changes of files
      git checkout -- .
      
      # OR discard changes of all files
      git reset --hard
      
      # OR stash to recovery later
      git stash
   ```

- Discard After staged but Before commit (after run `git add` and before run `git commit`).

   ```bash
   git reset HEAD [file_path]

   # Note that you can reset to older version
   git reset HEAD~1 [file_path]
   git reset HEAD~2 [file_path]
   git reset HEAD~3 [file_path]
   ```

- By combining `checkout` and `reset`, we can completely discard changes as below

   ```bash
   git reset --hard
   git checkout -- .
   ```

>For summary:
>`git checkout` will discard changes from `staging` -> `workspace` way.
>`git reset` will discard changes from `repo` -> `staging` way.


## Tracking remote branch

   ```bash
      # track remote develop branch to local develop branch
      git branch --set-upstream-to=origin/develop develop
   ```


## Switch commit

When we wanna switch to a commit, we can use `reset` command to reset current workspace to target commit.

- Reset with `--hard` option to remove changes by current workspace. Note that, newly added files will not be removed.

   ```bash
   git reset --hard [commit_id]
   ```

- Reset with `--soft`` option to 
