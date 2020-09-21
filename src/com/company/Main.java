package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    final public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        /*
        [0] - ALGORITHM(default - shift)
        [1] - MODE(default - encrypt)
        [2] - DATA(default - "")
        [3] - KEY(default - 0)
        [4] - IN(default - from console)
        [5] - OUT(default - to console)
         */
        Arguments[] arguments = Arguments.values();

        for (int i = 0; i < arguments.length; ++i) {
            arguments[i].setValue(parseArguments(args, arguments[i].getOperator()));
        }

        int key;
        if (arguments[3].notNull())
            key = Integer.parseInt(arguments[3].getValue());
        else
            key = 0;

        //#### READ TEXT ###########################
        String inputText = "";
        if (arguments[4].notNull()) {
            try {
                File file = new File(arguments[4].getValue());
                Scanner scanner = new Scanner(file);

                while (scanner.hasNext()) {
                    inputText += scanner.nextLine();
                }
                scanner.close();
            }catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else {
            if (arguments[2].notNull())
                inputText = arguments[2].getValue();
        }
        //###########################################################

        //###### ENCRYPT-DECRYPT #############################
        Algorithm algorithm;
        if (arguments[0].notNull())
        {
            if (arguments[0].getValue().equals("shift"))
                algorithm = new Shift(inputText,key);
            else
                algorithm = new Unicode(inputText,key);
        }
        else
            algorithm = new Shift(inputText,key);

        String outputText = "";
        if (arguments[1].notNull()) {
            if (arguments[1].getValue().equals("enc"))
                outputText = algorithm.encrypt();
            else
                outputText = algorithm.decrypt();
        }
        else
            outputText = algorithm.encrypt();
        //###########################################################

        //###### PRINT RESULT #####################################
        if (arguments[5].notNull()) {
            try {
                File file = new File(arguments[5].getValue());
                FileWriter writer = new FileWriter(arguments[5].getValue());

                writer.write(outputText);
                writer.close();
            }catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else
            System.out.println(outputText);
        //###########################################################
    }

    public static String parseArguments(String[] args, String operator)  {
        if (args.length == 0)
            return null;

        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals(operator))
                return args[i+1];
        }

        return null;
    }
}
