package adn;

public class MotifARN extends MotifADN {

	public MotifARN(String motif) throws IllegalArgumentException {
		super(motif);
	}
	
	private char nucleotideCompl(char nucleotide) {
		switch (nucleotide) {
			case 'A' : return 'U';
			case 'C' : return 'G';
			case 'G' : return 'C';
			default : return 'A';
		}
	}
	
	@Override
	public String getCompl() {
		StringBuilder sb = new StringBuilder();
		
		for (char nucleotide : this.getMotif().toCharArray())
			sb.append(this.nucleotideCompl(nucleotide));
		
		return sb.toString();
	}

}
