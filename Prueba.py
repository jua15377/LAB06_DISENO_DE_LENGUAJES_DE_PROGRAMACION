

def isNOmbreDeConjunto(entrada):
    tokenName = "letter"
    letter = "abcdfghijklmnopqrstuvwxyz"
    respuesta = entrada in letter
    if (respuesta):
        respuesta = '<%s, "%s">'%(tokenName, entrada)
       #print respuesta
    else:
        respuesta = ""
    return respuesta

def keywords(palabra):
    lista = ['if','whie','for']
    if palabra in lista:
        return True,lista[lista.index(palabra)]
    else:
        return False,""

fileName = 'test.txt'#raw_input("ingrese el archivo a lexear")
infile = open(fileName, 'r')

print('>>> Lectura del fichero linea a linea')
for line in infile:
    salida = ''
    palabra = ''
    #print (isNOmbreDeConjunto(line))
    for x in range(len(line)):
        letter = line[x]
        #try:
        if letter != " ":
            palabra = palabra + letter
            print palabra
            isKeyword, kindKeyword = keywords(palabra)
            if isKeyword == True:
                salida = salida + '<%s, "%s">'%(kindKeyword, palabra) + ' '
                palabra = ''
            else:
                salida = salida + isNOmbreDeConjunto(letter)
        else:
            pass
        #except e:
        #print "out of range"
    print salida
# Cerramos el fichero.
infile.close()
