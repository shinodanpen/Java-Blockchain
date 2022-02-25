package org.shinodanpen.blockchain;

import java.util.ArrayList;

public class Blockchain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args){

        blockchain.add(new Block("Esempio", "start"));

        //Let's create some blocks for testing
        int i = 1;
        while (true){
            Block newBlock = new Block(
                    "Esempio "+ i,
                    blockchain.get(blockchain.size() - 1).getHash());
            //Example of block mining
            int prefix = 3;
            addNewBlock(newBlock.mineBlock(prefix));
            if(!(isBlockchainValid())){
                System.err.println("BLOCKCHAIN IS INVALID");
                return;
            }
            i++;
        }
    }

    public static void addNewBlock(String data){
        blockchain.add(new Block(data, blockchain.get(blockchain.size() - 1).getHash()));
    }

    public static Boolean isBlockchainValid(){
        Block current, previous;
        for(int i = 1; i < blockchain.size(); i++){
            current = blockchain.get(i);
            previous = blockchain.get((i-1));

            if(!current.getHash().equals(
                    current.calculateHash())){
                System.err.println("ERROR - Hashes are not equal");
                return false;
            }

            if(!previous.getHash().equals(
                    current.getPrevHash())){
                System.err.println("ERROR - Previous hashes are not equal");
                return false;
            }
        }
        System.out.println("Blockchain is valid");
        return true;
    }

}
