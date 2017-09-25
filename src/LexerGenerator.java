import java.util.ArrayList;

public class LexerGenerator {
    public String textoFinal = "";
    private ArrayList<String> arreglorCharacters = new ArrayList<>();
    private ArrayList<String> arreglorKeywords = new ArrayList<>();

    public LexerGenerator(){

    }
    public void genarate(ArrayList<String> linesArray){
        String cojunto = "";
        for (String linea: linesArray) {
            // si no es una linea en blanco
            if (!linea.equals("")) {
                //si es una linea de comentario
                if (linea.charAt(0) == '/' && linea.charAt(1) == '/') {
                    System.out.println("Se encontro una linea con comentada\nComentario: ");
                    System.out.print(linea.replace("/", ""));
                    continue;
                }
                String[] lineaseparada = linea.split(" ");
                if(lineaseparada.length == 1){
                    switch (lineaseparada[0]){
                        case "CHARACTERS":
                            cojunto = "CHARACTERS";
                            break;
                        case "KEYWORDS":
                            cojunto = "KEYWORDS";
                            break;
                        case "IGNORE":
                            cojunto = "IGNORE";
                            break;
                        default:
                            continue;
                    }
                }
                //debe
                //System.out.println(lineaseparada.length);
                else if (lineaseparada.length == 3){
                    //System.out.println(lineaseparada[0]);
                    //System.out.println(lineaseparada[1]);
                    //System.out.println(lineaseparada[2]);
                    if (cojunto == "KEYWORDS"){
                        arreglorKeywords.add(lineaseparada[0]);
                    }
                    else if (cojunto == "CHARACTERS"){
                        arreglorCharacters.add(lineaseparada[0]);
                        String contenido =  prepareString(lineaseparada[2]);
                        charactersMethod(lineaseparada[0], contenido);
                    }
                }
            }
        }
        keyuwordMethod();
        principalMethod();
    }
    public String prepareString(String s){
        String s2 =  s.substring(1,s.length()-2);
        return s2;
    }
    public void charactersMethod(String nombreDeConjunto, String contenidoDelSet){
         textoFinal += "def is" + nombreDeConjunto + "(entrada):\n"+
                "\ttokenName = \"" +  nombreDeConjunto +"\"\n"+
                "\tletter = \""+ contenidoDelSet +"\"\n"+
                "\trespuesta = entrada in letter\n"+
                "\tif (respuesta):\n"+
                "\t\trespuesta = \'<%s, \"%s\">\'%(tokenName, entrada)\n"+
                "\telse:\n"+
                "\t\trespuesta = \"\"\n"+
                "\treturn respuesta\n\n";
    }
    public void keyuwordMethod(){
        String cadenaLista = "[";
        for (String item: arreglorKeywords){
            String item2 = "\'" + item +"\'";
            if(!item.equals(arreglorKeywords.get(arreglorKeywords.size()-1))) {
                cadenaLista += item2 + ",";
            }
            else {
                cadenaLista += item2 + "]";
            }
        }

        textoFinal += "def keyword(palabra):\n"+
                "   lista = " +  cadenaLista +"\n"+
                "   if palabra in lista:\n"+
                "       return True,lista[lista.index(palabra)]\n"+
                "   else:\n"+
                "       return False,\"\"\n";
    }
    public void principalMethod(){
         textoFinal += "#abre el archivo\n" +
                "fileName = raw_input(\"ingrese el archivo a lexear\")\n" +
                "infile = open(fileName, 'r')\n" +
                "salidaSize = 0\n" +
                "\n" +
                "for line in infile:\n" +
                "    salida = ''\n" +
                "    palabra = ''\n" +
                "     \n" +
                "    wordsInLine = line.split(\" \")\n" +
                "    wordsInLine2 = line.split(\" \")\n" +
                "    for w in wordsInLine:\n" +
                "        isKeyword, kindKeyword = keyword(w)\n" +
                "        if isKeyword:\n" +
                "            salida = salida + '<%s, \"%s\">' % (kindKeyword, w) + ' '\n" +
                "            wordsInLine2.remove(w)\n" +
                "        else:\n" +
                "            for caracter in w:\n" +
                "                if caracter != ' ' and caracter != '\\n' and caracter != \"\\t\":\n" +
                "                    salidaSize = len(salida)\n";
        //agrega dianmicamente los subconjuntois de characters
        for (String palabra: arreglorCharacters){
            textoFinal += "\n                    cadenaTemp = is" +palabra+"(caracter)\n" +
                    "                    if cadenaTemp != \"\":\n" +
                    "                        salida = salida + cadenaTemp";
        }


                textoFinal +="                    if salidaSize == len(salida):\n" +
                "                        print 'ERROR: El caracter [%s] no es reconocido por el lexer!!!'%(caracter)\n" +
                "                else:\n" +
                "                    print 'se enccontro espacio en blanco'\n" +
                "    print salida\n" +
                "# Cerramos el fichero.\n" +
                "infile.close()";
    }
}
