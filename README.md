jtopia
======

Java clone for Python term extractor topia.It will extract important keywords/key phrases from text.
https://github.com/turian/topia.termextract

jtopia is a light-weight term extractor, which is domain independent in nature.
jtopia uses a rule based + POS tagged based approach to find out the keywords / key phrases.

You can even tune the parameters in jtopia to get some filtered keywords / key phrases.

jtopia just throws n number of keywords from the input text. It does not rank the keyword as first or second.

The numbers in the square is just an information about the extracted term. 

Hurricane Saturday Night=[1, 3]. Here 1 means the extracted keyword "Hurricane Saturday Night" has frequency 1 in the input text. 
The number 3 means the keyword is formed using 3 words "Hurricane", "Saturday" and "Night".

By default , jtopia is using a standard POS tagged lexicon from an english text to prove the keyword extraction strategy.So all the power of jtopia lies in the POS tagged lexicon (model/english-lexicon.txt). 

Dependency project
==================

* [StringHelpers](https://github.com/srijiths/StringHelpers)

Expansion of jtopia in your domain
==================================

If you want to expand the power of jtopia , have a look at the below points.

* Add more POS tagged words from your domain to model/english-lexicon.txt by preserving the current form. 

* Instead of using model/english-lexicon.txt , use another POS tagger ( Stanford POS tagger / OpenNLP POS Tagger ) and make the POS output available to TermExtractor class.
	You can set this by passing "taggerType" to Configuration class. The values are "default","openNLP" or "stanford".

Fine tuning jtopia
==================

* You can change the extracted terms output using the TermsFilter class.There are two parameters (singleStrengthMinOccur and noLimitStrength) which filters the extracted jtopia output according to the parameters. 

You can set this filters in TermsExtractor class as TermsFilter termsFilter = new TermsFilter(3, 2);
Thease values (3,2) i set by default.

This act as a fine tuning parameter in the post processing phase of jtopia.Here what jtopia does is clean the possible junk keywords from the entire extracted keyword set by applying these filter parameters.
Nobody wants keyword explosion from an input text , instead every one wants maximum tuned feasible keywords. 

jtopia Hints
============
 
* If you are dealing short text , then apply singleStrengthMinOccur,noLimitStrength to minimum will give you maximum possible keywords from the text.
* If you are dealing large text then apply singleStrengthMinOccur, noLimitStrength to a feasible maximum to chop out all the unwanted junk keywords, which gives you minimum keywords which are best suited for the text.

How to Use
==========
For more help on how to use 
https://github.com/srijiths/jtopia/wiki

Or take a look at JtopiaUsage.java

License
=======
Apache License 2 - http://www.apache.org/licenses/LICENSE-2.0.html