package org.all.info.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/* This class includes start methods of all parsers */
public class Run {

    private static Logger log = LogManager.getLogger(Run.class);

    public static void main(String[] args) {

        new Thread(new MatchIDParser()).run();

//        log.info("Choose:");
//        log.info("1. Parsing match_seq_num and mach_id");
//        log.info("2. Parsing match info");
//
//        Scanner in = new Scanner(System.in);
//        String choose = in.nextLine();
//
//        while(true){
//            switch (choose){
//                case "1":{
//                    new Thread(new MatchIDParser()).run();
//                    break;
//                }
//                case "2":{
//
//                }
//            }
//        }


    }
}
