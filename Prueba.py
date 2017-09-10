def isNOmbreDeConjunto(entrada):
    tokenName = "letter"
    letter = "abcdfghijklmnopqrstuvwxyz"
    respuesta = entrada in letter
    if (respuesta):
        print '<%s, "%s">'% (tokenName, entrada)

def keywords(palabra):


fileName = raw_input("ingrese el archivo a lexear")
infile = open(fileName, 'r')

print('>>> Lectura del fichero linea a linea')
for line in infile:
    palabra = ""
    print (isNOmbreDeConjunto(line))
    for x in range(len(line)-1):
        letter = line[x]
        try:
            if
        except:
            print "out of range"
# Cerramos el fichero.
infile.close()
