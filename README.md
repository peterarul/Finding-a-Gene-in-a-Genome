# Finding-a-Gene-in-a-Genome
An important problem genomics scientists encounter regularly: how to identify genes in a strand of DNA. To tackle this problem, we  need to understand strings: series of characters such as letters, digits, punctuation, etc. A gene for a protein has a start codon, followed by a substring that is a multiple of 3, followed by a stop codon.

Algorithm to identify a gene in a strand of DNA:

1. Identify the first start codon ATG in the string.
2. Identify the first occurrence of the stop codon TAG after the start codon. If the length of the substring between the start and stop codon is a multiple of three, the gene is the string from the beginning of the start codon to the end of the TAG stop codon.
3. If no gene is found yet, then identify the first occurrence of the stop codon TGA after the start codon. If the length of the substring between the start and stop codon is a multiple of three, the gene is the string from the start codon to the TGA stop codon.
4. If no gene is found yet, then identify the first occurrence of the stop codon TAA after the start codon. If the length of the substring between the start and stop codon is a multiple of three, the gene is the string from the start codon to the TAA stop codon.
5. If no gene is found yet, then the gene returned is the empty string.
6. If a gene was found, then print the gene, and also print the stop codon that was used to find the gene.
7. The program should work regardless of whether the DNA strand uses uppercase or lowercase letters.

Example strings used to test might:

1. “AATGCTAGTTTAAATCTGA”—This has all three stop codons. Using the algorithm above, the first start codon ATG is found. The stop codon TAG is found first but is not a multiple of three. The stop codon TGA is found next and is a multiple of three away, so “ATGCTAGTTTAAATCTGA” is the string returned.
2. “ataaactatgttttaaatgt”—Using the algorithm above, the first occurrence of ATG is found. Then TAG is not found, TGA is not found, but TAA is found and is a multiple of three from ATG. The string “atgttttaa” is returned. Note that TAA occurs twice in the string. The program should look for the first one that occurs after the start codon in the string.
3. “acatgataacctaag” returns “”—Note the first TAA is found and does not work. A second TAA is a multiple of three away, but our algorithm only looks for the first occurrence of TAA immediately past the start codon.
