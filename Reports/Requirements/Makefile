TEXFILES=$(shell find . -type f -name '*.tex')
BIBFILES=$(shell ls *.bib)
PDFLATEX=pdflatex
.PHONY: all

all: main.pdf


main.pdf: $(TEXFILES) $(BIBFILES) 
	#$(PDFLATEX) main
	#makeglossaries main
	$(PDFLATEX) main
	while grep "Rerun to get cross" main.log; do pdflatex main; done
	bibtex main
	$(PDFLATEX) main
	while grep "Rerun to get cross" main.log; do pdflatex main; done

.PHONY: clean

clean:
	rm -f *.aux *.nav *.out *.snm *.toc *.log *.bbl *.idx *.ind *.ilg *.blg *.lot *.lof *~ *.ist *.acn *.glo *.gls *.acr *.alg *.backup *.pdf *.glg

test: all
	evince main.pdf
