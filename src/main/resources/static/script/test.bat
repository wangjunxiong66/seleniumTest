echo Run Tesseract for Training.. 
tesseract font.tif font batch.nochop makebox
tesseract font.tif font nobatch box.train
 
echo Compute the Character Set.. 
unicharset_extractor font.box

echo Mftraining .. 
mftraining -F font_properties -U unicharset -O font.unicharset font.tr

echo Clustering.. 
shapeclustering -F font_properties -U unicharset font.tr

echo Cntraining .. 
cntraining font.tr

echo Rename Files.. 
rename normproto font.normproto 
rename inttemp font.inttemp 
rename pffmtable font.pffmtable 
rename shapetable font.shapetable  

echo Create Tessdata.. 
combine_tessdata  font.
