package org.shinodanpen.blockchain;

import java.util.ArrayList;

public class Blockchain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args){

        blockchain.add(new Block("Esempio", "start"));

        //Let's create some blocks for testing
        int i = 1;
        int prefix = 3;
        while (true) {
            Block newBlock = new Block(
                    "Esempio " + i,
                    blockchain.get(blockchain.size() - 1).getHash());
            //Example of block mining

            int counter = 0;
            String minedHash;
            do {
                minedHash = newBlock.mineBlock(prefix);

            } while (!isBlockValid(newBlock, minedHash));
            blockchain.add(newBlock);
        }
    }

    public static Boolean isBlockValid(Block newBlock, String minedHash){
        String calculatedHash = newBlock.getHash();

        if(!(minedHash.equals(calculatedHash))){
            System.err.println("ERROR - Wrong hash.");
            return false;
        }
        System.out.println("INFO - Correct hash, adding block to chain.");;
        return true;
    }

}
