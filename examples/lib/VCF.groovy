@GrabResolver(name='genepi-maven', root='https://genepi.i-med.ac.at/maven')
@Grab(group='lukfor', module='pgs-calc', version='1.6.1')


import java.util.Map;
import genepi.riskscore.io.vcf.*;

class VCF {

		static Map load(String vcfFilename) throws IOException {

			def vcf = [:]
			vcf.contigs = new HashSet<String>()
			vcf.variants = new Vector<String>()

			FastVCFFileReader reader = new FastVCFFileReader(vcfFilename)
			while(reader.next()){
				def variant = reader.get()
				vcf.contigs << variant.contig
				vcf.variants << [
				        contig: variant.contig,
						position: variant.start,
						ref: variant.referenceAllele,
						alt: variant.alternateAllele
				]
			}
			reader.close()

			vcf.samples = reader.genotypedSamples

			return vcf;
		}
}