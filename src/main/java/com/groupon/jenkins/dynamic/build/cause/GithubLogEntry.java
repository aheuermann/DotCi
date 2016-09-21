/*
The MIT License (MIT)

Copyright (c) 2014, Groupon, Inc.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package com.groupon.jenkins.dynamic.build.cause;

import hudson.model.User;
import hudson.scm.ChangeLogSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.kohsuke.github.GHCommit;
import org.kohsuke.stapler.export.Exported;

public class GithubLogEntry extends ChangeLogSet.Entry {

    private final String message;
    private final String githubUrl;
    private final String commitId;
    private final List<String> affectedPaths;

    public GithubLogEntry(String message, String githubUrl, String commitId, List<String> affectedPaths) {
        this.message = message;
        this.githubUrl = githubUrl;
        this.commitId = commitId;
        this.affectedPaths = affectedPaths;
    }

    public GithubLogEntry(GHCommit commit) throws IOException {
        this.message = commit.getCommitShortInfo().getMessage();
        this.githubUrl = commit.getHtmlUrl().toExternalForm();
        this.commitId = commit.getSHA1();
        List<GHCommit.File> files = commit.getFiles();
        this.affectedPaths = new ArrayList<>();
        for (GHCommit.File file : files) {
            this.affectedPaths.add(file.getFileName());
        }
    }


    @Override
    @Exported
    public String getMsg() {
        return this.message;
    }

    @Override
    @Exported
    public User getAuthor() {
        return null;
    }

    @Override
    @Exported
    public Collection<String> getAffectedPaths() {
        return affectedPaths;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    @Override
    public String getCommitId() {
        return commitId;
    }

}
