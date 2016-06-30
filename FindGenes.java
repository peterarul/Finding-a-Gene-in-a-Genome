import java.io.*;
import edu.duke.FileResource;
import edu.duke.StorageResource;
import edu.duke.DirectoryResource;

public class FindGenes {
    public int findStopIndex(String dna, int index) {
        int stop1 = dna.indexOf("TGA", index);
        if (stop1 == -1 || (stop1 - index) % 3 != 0) {
            stop1 = dna.length();
        }
        int stop2 = dna.indexOf("TAA", index);
        if (stop2 == -1 || (stop2 - index) % 3 != 0) {
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("TAG", index);
        if (stop3 == -1 || (stop3 - index) % 3 != 0) {
            stop3 = dna.length();
        }
        return Math.min(stop1, Math.min(stop2, stop3));
}
 
public StorageResource storeAll(String dna) {
    //CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCAdna = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
    String sequence = dna.toUpperCase();
    StorageResource store = new StorageResource();
    int index = 0;
    while (true) {
        index = sequence.indexOf("ATG", index);
        if (index == -1)
        break;
        int stop = findStopIndex(sequence, index + 3);
        if (stop != sequence.length()) {
            String gene = dna.substring(index, stop + 3);
            store.add(gene);
            System.out.println("From: " + index + " to " + stop + " Gene: " + gene );//index = sequence.substring(index, stop + 3).length();
            index = stop + 3; // start at the end of the stop codon
        }else{ index = index + 3;
        }
 
    }
 
    return store;//System.out.println(sequence);
 
}
 
public void testStorageFinder() {
    DirectoryResource dr = new DirectoryResource();
    StorageResource dnaStore = new StorageResource();
    for (File f : dr.selectedFiles()) {
        FileResource fr = new FileResource(f);
        String s = fr.asString();
        dnaStore = storeAll(s);
        printGenes(dnaStore);
    }
    System.out.println("size = " + dnaStore.size());
 
}
 
public String readStrFromFile(){
    FileResource readFile = new FileResource();
    String DNA = readFile.asString();
    System.out.println("DNA: " + DNA);
    return DNA;
 
}//end readStrFromFile() method;
 
public float calCGRatio(String gene){
    gene = gene.toUpperCase();
    int len = gene.length();
    int CGCount = 0;
    for(int i=0; i<len; i++){
        if(gene.charAt(i) == 'C' || gene.charAt(i) == 'G')
        CGCount++;
    }//end for loop
    System.out.println("CGCount " + CGCount + " Length: " + len + " Ratio: " + (float)CGCount/len);
    return (float)CGCount/len;
 
}//end of calCGRatio() method;
 
public void printGenes(StorageResource sr){
    for(String gene: sr.data()){
        if (gene.length() > 60) {
            System.out.println("GREATER THAN 60: "+gene.length()+":::::::"+gene);
        }
        if(calCGRatio(gene)> 0.35) {
            System.out.println("GREATER THAN 0.35"+gene.length()+":::::::"+gene);
        }
 
    }
    //create a FindMultiGenesFile object FMG
    FindGenes FMG = new FindGenes();
    //read a DNA sequence from file
    String dna = FMG.readStrFromFile();
    FMG.storeAll(dna);
    //store all genes into a document
    StorageResource dnaStore = FMG.storeAll(dna);
    int longerthan60 = 0;
    int CGGreaterthan35 = 0;
    System.out.println("\n There are " + dnaStore.size() + "genes");
    int countone = 0;
    int counttwo = 0;
    int longest = 0;
    for(String d : dnaStore.data() ){
        if (d.length()> 60){
            countone++;
            System.out.println("There are " + dnaStore.size() + "genes longer than 60 " + countone);
        }

        if(calCGRatio(d) > 0.35){
            counttwo++;
            System.out.println("There are " + counttwo + "genes with CG ratio greater than 0.35");
        }
        if (d.length() > longest){
            longest = d.length();
            System.out.println("Longest gene length is "+ longest);
        }
        System.out.println("Longest gene length is "+ longest);
    }
    System.out.println("Longest gene length is "+ longest);

    int index = dna.indexOf("CTG");
    int count = 0;
    while (index != -1) {
        count++;
        dna = dna.substring(index + 3);
        index = dna.indexOf("CTG");
    }
    System.out.println("No of *CTG* in the input is : " + count);
    //System.out.println("There are " + longerthan60 + "genes longer than 60");
    //calCGRatio(gene)
    //System.out.println("There are " + CGGreaterthan35 + "genes with CG ratio greater than 0.35");
 
}
}
