package xyz.sheba.commit_template.utils;

import cucumber.api.java.hu.Ha;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Command {
    private final File workingDirectory;
    private final String command;

    public Command(File workingDirectory, String command) {
        this.workingDirectory = workingDirectory;
        this.command = command;
    }

    public static class Result {
        static Result ERROR = new Result(-1);

        private final int exitValue;
        private final List<String> output;
        private final HashMap<String, Integer> outputHash;

        Result(int exitValue) {
            this.exitValue = exitValue;
            this.output = null;
            this.outputHash = null;
        }

        Result(int exitValue, List<String> output) {
            this.exitValue = exitValue;
            this.output = output;
            this.outputHash = getOutputHash();
        }

        public boolean isSuccess() {
            return exitValue == 0;
        }

        public List<String> getOutput() {
            return output;
        }

        private HashMap<String, Integer> getOutputHash() {
            HashMap<String, Integer> outputHash = new HashMap<>();
            for (int i = 0; i < output.size(); i++){
                outputHash.put(output.get(i), i);
            }
            return outputHash;
        }

        public int compare(String s1, String s2) {
            return outputHash.get(s1) - outputHash.get(s2);
        }
    }

    public Result execute() {
        try {
            Process process = new ProcessBuilder("/bin/sh", "-c", this.command)
                    .directory(workingDirectory)
                    .start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            List<String> output = reader.lines().collect(Collectors.toList());

            process.waitFor(2, TimeUnit.SECONDS);
            process.destroy();
            process.waitFor();

            return new Result(process.exitValue(), output);
        } catch (Exception e) {
            return Result.ERROR;
        }
    }

}