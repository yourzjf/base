package net.yourzjf.corejava.apachecli;

import org.apache.commons.cli.*;

import java.util.Random;

public class ApacheCLIDemo {
    private static final String HELP_DESCRIPTION = "description";
    private static final String HELP_IPADDRESS = "ip address";
    private static final String HELP_PORT = "port";
    private static final String HELP_PROTOCOL = "protocol";

    public static void main(String[] args) {
        simpleTest(args);
    }

    public static void simpleTest(String[] args) {
        Options opts = new Options();
        opts.addOption("h", false, HELP_DESCRIPTION);
        opts.addOption("i", true, HELP_IPADDRESS);
        opts.addOption("p", true, HELP_PORT);
        opts.addOption("t", true, HELP_PROTOCOL);
        CommandLineParser parser = new DefaultParser();
        CommandLine cl;
        try {
            cl = parser.parse(opts, args);
            if (cl.getOptions().length > 0) {
                if (cl.hasOption('h')) {
                    HelpFormatter hf = new HelpFormatter();
                    hf.printHelp("Options", opts);
                } else {
                    String ip = cl.getOptionValue("i");
                    String port = cl.getOptionValue("p");
                    String protocol = cl.getOptionValue("t");
                    if("127.0.0.1".equals(ip))
                    {
                        System.err.println("INVALID_IP");
                        System.exit(1);
                    }
                    try {
                        int rc = new Random().nextInt();
                        if (rc == 0) {
                            System.out.println("RMDATASOURCE_SUCCEEDED");
                        } else {
                            System.err.println("RMDATASOURCE_FAILED");
                        }
                    } catch (Exception e) {
                        System.err.println("RMDATASOURCE_FAILED");
                        e.printStackTrace();
                    }
                }
            } else {
                System.err.println("ERROR_NOARGS");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}