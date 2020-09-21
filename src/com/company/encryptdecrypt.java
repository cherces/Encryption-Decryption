package com.company;

class Algorithm {
    protected String inputText;
    protected String outputText;
    protected int key;

    public Algorithm(String inputText, int key) {
        this.inputText = inputText;
        this.key = key;
    }

    public String encrypt() {
        return null;
    }

    public String decrypt() {
        return null;
    }
}

class Shift extends Algorithm {

    private char[] alphabetLower = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private char[] alphabetUpper = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public Shift(String inputText, int key) {
        super(inputText, key);
    }

    @Override
    public String encrypt() {
        this.outputText = "";

        for (int i = 0; i < inputText.length(); ++i) {
            char sym = inputText.charAt(i);

            if ((int)sym > 96 && (int)sym < 123) {
                for (int j = 0; j < 26; ++j) {
                    if (alphabetLower[j] == sym)
                        outputText += (alphabetLower[(j + this.key) % 26]);
                }
            }
            else if ((int)sym > 64 && (int)sym < 91) {
                for (int j = 0; j < 26; ++j) {
                    if (alphabetUpper[j] == sym)
                        outputText += (alphabetUpper[(j + this.key) % 26]);
                }
            }
            else
                outputText += sym;
        }

        return outputText;
    }

    @Override
    public String decrypt() {
        this.outputText = "";

        for (int i = 0; i < inputText.length(); ++i) {
            char sym = inputText.charAt(i);

            if ((int)sym > 96 && (int)sym < 123) {
                for (int j = 0; j < 26; ++j) {
                    if (alphabetLower[j] == sym) {
                        int it = j - this.key;
                        if (it < 0) {
                            it *= -1;
                            outputText += (alphabetLower[(26 - it) % 26]);
                        }
                        else
                            outputText += (alphabetLower[ it % 26]);
                    }
                }
            }
            else if ((int)sym > 64 && (int)sym < 91) {
                for (int j = 0; j < 26; ++j) {
                    if (alphabetUpper[j] == sym) {
                        int it = j - this.key;
                        if (it < 0) {
                            it *= -1;
                            outputText += (alphabetUpper[(26 - it) % 26]);
                        }
                        else
                            outputText += (alphabetUpper[ it % 26]);
                    }
                }
            }
            else
                outputText += sym;
        }

        return outputText;
    }
}

class Unicode extends Algorithm {

    public Unicode(String inputText, int key) {
        super(inputText, key);
    }

    @Override
    public String encrypt() {
        this.outputText = "";

        for (int i = 0; i < inputText.length(); ++i) {
            outputText += (char)((inputText.charAt(i) + this.key) % 127);
        }

        return outputText;
    }

    @Override
    public String decrypt() {
        this.outputText = "";

        for (int i = 0; i < inputText.length(); ++i) {
            char s = inputText.charAt(i);

            if (key > s) {
                outputText += (char)(127 - (this.key - s));
            }
            else {
                outputText += (char)(s - this.key);
            }
        }

        return outputText;
    }
}
