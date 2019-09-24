package labOTP;

import WorkWithStrings.stringXORer;

import java.util.*;

public class main {

    private static ArrayList<String> filesCipherText = new ArrayList<String>();
    final private static int firstCapitalASCII = 64;
    final private static int lastCapitalASCII = 91;
    final private static int firstSmallASCII = 96;
    final private static int lastSmallASCII = 123;
    final private static int spaceASCII = 32;

    public static void main(String[] args){

        String[] arrayOfHexMessages = new String[11];
        arrayOfHexMessages[0] = "f9e4d9bff5bac624e2ce1bb3c4cce85336d650082caa9037eb48f6a5aedf9ff6f8f4daa2a3e358a783321dd0d09ffa57f9528f4551c65a726620117c81ff867786c50dfaed6056c670a8103b4dab6fe7cfae8bfb71803aa04023e0271b2bc012e10e09901d58c2d654d34004109da5ec79e55f8b42c624ce6c6befeb44edb8f36229c19e32fe40e7596bca";
        arrayOfHexMessages[1] = "ebf495b9e6f4912df6c10bfcc69ef3593294520461ad9b2fa400e3f1b1d79fbeb6ead8e3a5fe5eead73917d2d28fe312e944c20a6bc60931666e52729fe28c25c0d71ae1ec7756dd7eed532741b679ed9da39a8f3f993cba516eee720c64c85cb5391d95164f818500c808031e9daba1";
        arrayOfHexMessages[2] = "fae99cfcfabd8527a3d907b5d88bbc5a31994b0961839020b215acf4f9df98beb6ead8e3baf20da9d12802c9cf8dfc53fb49c41775831970696e556f84fa8c77818402e1f63219d438ab513b5bbf2de1dcfcc9ee7cd717b54623c3681121cf";
        arrayOfHexMessages[3] = "fae99cfcf7bd962ae6df1bb9ce98bc4b21995a0822ad9165a900e3e4f9c18effb3a5caadaee554bad7381dd3808be255e453c8116ece5a7d68215a6ecded9a7787cb01eaa27305927ba4403d5db479e7c5fa9abe239837a14b66e5271d3d8753b50f1c8b1c53c1d645d20314089fbaa562e30d8f4b813fd5687af4a649e0cacc6825df8362967bfc5863d6825c5365e3";
        arrayOfHexMessages[4] = "f7ee8cfcf0bb8865f78d18bdd898bc4f3cd65c0838e89465b81cb7a5b6d0cbfdb9f78fa8a8ee5eeac5231dd0808bae55fe5881126ecc5a62772b52748ce0802d85d74ee7ec3205c67dac5c3c56a12de1dcfcc9ee7cd71eb55a60a1551030c25cf7191a9e535ec99b4dd90e121881a9ec62e30dad4b8f20d7647c";
        arrayOfHexMessages[5] = "fae99caef1f48730e68d1babd9cce84223934d5d2eaed526b900b3f1b6d199ffa8edd6e3e0b759a2c22552cac883ed5aab56c8096a831174623e116e88ef9b3294d74efde37413927ebf5f3818bf62f7cfaed6a725833fb10870e8740b21d51eb51d069d5349ce97549c170e188ca6ec7ae44182078d35c2712eefae0abf8fe8736cc08b74d301f34761ded0485d7eff397146ba3e57082d2bf3973555225dd56c959411d999165fd45718ae";
        arrayOfHexMessages[6] = "fae99caef1f48730e68d1babd9cce84223934d5d2eaed526b209b7eabec48aeeb0fc95e3a2f948ead73913c9808be25ee456d24572cb1f31402147789fe284328ed04efaed3203c17ded52274db268a2dbe1c8ad34d727bb0861f3621e2f8746fd19489a1c59c3da00dd0e025180a0a92df9458f53c622c2707bf5b90cbecae8682993ad7dc044e75b63d69e45127fe239635aa97b4714353af8c37317707cc239829e11e8881b50da1e04b373";
        arrayOfHexMessages[7] = "f9e4d9bff5bac631e6c84fa8de89bc4b3c9f500961bf9d20b91ce3f1b1d3cbfdb0ecdfe3a4e40dbfcd3913cdd093ae5bed01c04571d1157f606e537499ac8024c0d70be0f63217dc7ced533a56b578efd8fd9aa33e8536f4586cf6620d64c140fa11488d1b5886934eca09141e81a3a963f90dc307a734ce215df4aa04a498";
        arrayOfHexMessages[8] = "efa1d1ace6bd9023f7c842b7d395b51b7393501e33b18531a216ada5aad583fbb5e08fb0b9f659afd071419dc186e95df948d50d6bd05631692f5c7881f5c936c0d41ce1e17712c76aa8103357b42de5d8e0dfbc30833aba4f23ea6206378b12f45c188b1c5ec39255ce05461780bcec68e34e9c5e9624ce6f69b0eb08a38ebc616cc3987dd544f1407cd6d0575d79ad7d734abe2255122920facd2a";
        arrayOfHexMessages[9] = "fae99cfcd7bb8821eade0afcf994fa5421927a1422bc9c2aa518b1fcf99ed9aee8b386e3a9f24ba3cd34019dc398f742ff4e810475830e79626e506f99ac8631c08419fceb661fdc7fed5f754ae67eedd1f8d3a036d730bb4c66f229";

        arrayOfHexMessages[10] = "fae99cfce7b18530e6d94fb1d39fef5a34931e1432f2d512a31cada5acc582f0bfa5cee3bee35fafc23c52dec99ae657f90d810b63d51f63273b4278cdf88132c0cf0bf7a27f19c07ded443d59a82dedd3eddf";

        String secretMessageHex = "fae99cfce7b18530e6d94fb1d39fef5a34931e1432f2d512a31cada5acc582f0bfa5cee3bee35fafc23c52dec99ae657f90d810b63d51f63273b4278cdf88132c0cf0bf7a27f19c07ded443d59a82dedd3eddf";
        String secretMessage = hexToString(secretMessageHex);
        String[] arrayOfASCIIEncMessages = hexToString(arrayOfHexMessages); // converting all the hex to ASCII
        stringXORer stringXORer = new stringXORer();
        HashMap<Integer,String> finalKeyNonHex = new HashMap<Integer, String>();
        Set<Integer> knownKeyPositiosn = new TreeSet<Integer>();
        String[] finalKey = new String[100];



        for(int i =  0; i < arrayOfASCIIEncMessages.length; i++){
            HashMap<Integer, Integer> counter = new HashMap();
            ArrayList<Integer> knownSpaceIndex = new ArrayList();

            for(int j = 0; j < arrayOfASCIIEncMessages.length; j++){
                if(!(i==j)){ //we cannot be testing a cipher against itself

                    String xorResult = stringXORer.encode(arrayOfASCIIEncMessages[i],arrayOfASCIIEncMessages[j]);

                    for(int k = 0; k < xorResult.length();k++){

                        //now we check if the result is an ASCII character
                        if(isCharASCIIAlphabet(String.valueOf(xorResult.charAt(k)))){
                            if(!counter.containsKey(k)){
                                counter.put(k, 1);
                            } else {
                                counter.put(k,counter.get(k) + 1);
                            }
                        }

                    }
                }
            }

            Iterator it = counter.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry pair = (Map.Entry)it.next();
                //if the count is likely above 6 then we can say this is probably the right character for the key
                if((Integer)pair.getValue() > 6){
                    knownSpaceIndex.add((Integer)pair.getKey());
                }
            }

            //if we xor string with spaces we can then output the key
            String xorWithSpaces = stringXORer.encode(arrayOfASCIIEncMessages[i],generateSpaceString(arrayOfASCIIEncMessages[i]));
            for(int l : knownSpaceIndex){
                finalKeyNonHex.put(l,String.valueOf(xorWithSpaces.charAt(l)));
                finalKey[l] = String.valueOf(xorWithSpaces.charAt(l));
                knownKeyPositiosn.add(l);

            }
        }

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int s = 0;

        for(int m = 0; m < finalKey.length; m++){
            if(finalKey[m] != null){
                sb1.append(finalKey[m]);
                sb2.append(finalKey[m]);
            } else {
                sb1.append("*");
                sb2.append((char)s);
            }
        }

        String manualKeyGuess = "it takes a great deal of bravery to stand up to our enemies but just as much to stand up to our friends";

        manualKeyGuess.replace("*",String.valueOf((char)s));

        System.out.println("key is : " + sb1.toString());
        for(int i = 0; i < arrayOfASCIIEncMessages.length; i++){
            System.out.println("Message " + i + " is: " +createSplitStringArray(arrayOfASCIIEncMessages[i],manualKeyGuess));
        }



        //System.out.println("message is : " + createSplitStringArray(arrayOfASCIIEncMessages[4],manualKeyGuess));



    }

    public static String createSplitStringArray(String inputString, String key){
        int kLength = key.length();
        int sLength = inputString.length();
        boolean exact = false;
        String[] splitStrings;
        String[] splitStringsDecoded;
        stringXORer xoRer = new stringXORer();
        StringBuilder sb = new StringBuilder();

        if(sLength%kLength == 0){
            splitStrings = new String[sLength/kLength];
            splitStringsDecoded = new String[sLength/kLength];
        } else {
            splitStrings = new String[(sLength/kLength) + 1];
            splitStringsDecoded = new String[(sLength/kLength) +1];

        }

        for(int i = 0; i < splitStrings.length; i++){
            if((i*kLength) + kLength -1 < inputString.length()){
                splitStrings[i] = inputString.substring(i*kLength,(i*kLength) + kLength);
            } else {
                splitStrings[i] = inputString.substring(i*kLength,inputString.length() -1);
            }

        }

        for(int j = 0; j < splitStringsDecoded.length; j++){

            splitStringsDecoded[j] = xoRer.encode(splitStrings[j],key);
        }

        for(String s : splitStringsDecoded){
            sb.append(s);
        }


        return sb.toString();
    }

    public static String[] hexToString(String[] hexStrings){
        stringXORer stringXORer = new stringXORer();
        String[] asciiiString = new String[hexStrings.length];

        for(int i = 0; i < hexStrings.length; i++){
            asciiiString[i] = stringXORer.convertHexToString(hexStrings[i]);
        }
        return asciiiString;
    }

    public static String hexToString(String hexString){
        stringXORer stringXORer = new stringXORer();
        return stringXORer.convertHexToString(hexString);
    }

    public static boolean isCharASCIIAlphabet(String s){

        return s.matches("[a-zA-Z]");
    }

    public static String generateSpaceString(String s){
        String retString = "";

        for(int i = 0; i < s.length(); i++){
            retString = retString + " ";
        }
        return retString;

    }


}
