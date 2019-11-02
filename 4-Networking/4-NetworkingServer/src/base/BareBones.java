package base;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BareBones {
    private Integer currentLineNum;
    private String currentLine;
    private HashMap<String, Integer> variables;
    private Stack<Integer> callStack = new Stack<>();	//every endFunc Statment, pop last stack, goto line after
    private List<String> allLines;
    private HashMap<Integer, Integer> loops; //<END, START>
    private HashMap<String, Integer> functionDefinitions;
    private boolean runnable;

	/*
		implemented comments with: 	//
		multi line comments with	 / *
						....
						* /
		subroutines:
			need to define subroutine first, before they are used (like python)
			defining: deffunc
						....
					  endfunc
			running: run ...
	 */

    public BareBones() {
        //read text file line by line
        currentLineNum = 0;
        callStack = new Stack();
        variables = new HashMap<String, Integer>();
        functionDefinitions = new HashMap<String, Integer>();
        loops = new HashMap<Integer, Integer>(); 	//<END, START>
        currentLine = "";
        System.out.println("Enter File Path: ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        try {
            allLines = Files.readAllLines(Paths.get(input));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        loops = precompileLoops();
        showHashMap(loops);
        execute();
    }

    public void execute(){
        String[] parts = {""};
        String opcode = "";
        String varName;
        try {
            parts = decodeLine(currentLineNum);
            currentLine = allLines.get(currentLineNum);
            opcode = parts[0].trim();
            opcode = opcode.toLowerCase();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("END");
            System.exit(0);
        }

        System.out.print("Current Line: ");
        System.out.println(currentLine);

        if (runnable) {
            //for exception cases: END, missing variable
            try {
                varName = parts[1];
                if(variables.containsKey(varName) == false && !opcode.equals("clear") && !opcode.equals("run")) {
                    error("Variable "+ varName +" not declared yet");
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                varName = "";
            }

            if (currentLine.startsWith("//")) {
                System.out.println("Commented at "+currentLineNum);
            }
            else if (currentLine.startsWith("/*")) {
                runnable = false;
            }
            else if (opcode.equals("while")) {
                System.out.println("RAN while");
                //get variable val from hashmap
                int value = variables.get(varName);
                //check statement
                //EXPAND ON LATER
                boolean statement;
                if (value != 0) {
                    statement = true;
                } else {
                    statement = false;
                }

                if (statement == false) {
                    //goto corresponding end
                    int endLine;
                    endLine = getKeyForValue(loops,currentLineNum);
                    skipToLine(endLine + 1);

                }
            }
            else if (opcode.equals("end")) {
                System.out.println("RAN END");
                //goto corresponding while statement
                int whileLine;
                whileLine = loops.get(currentLineNum);
                skipToLine(whileLine);

            }
            else if (opcode.equals("clear")) {
                variables.put(varName, 0);
            }
            else if (opcode.equals("incr")) {
                int value = variables.get(varName);
                variables.put(varName, value + 1);

            }
            else if (opcode.equals("decr")) {
                int value = variables.get(varName);
                variables.put(varName, value - 1);
            }
            else if (opcode.equals("run")){
                if (functionDefinitions.containsKey(varName)) {
                    //goto deffunc
                    callStack.push(currentLineNum);
                    //hashmap func name as key, val is line its def on
                    skipToLine(functionDefinitions.get(varName) + 1);
                } else {
                    error("function " + varName +" at " + currentLineNum + " doesnt exist");
                }
            }
            else if (opcode.equals("endfunc")) {
                int n = callStack.pop() + 1;
                System.out.println("go back to: " + n);
                skipToLine(n);
            }
            else {
                error("BROKEN");
            }
        }

        //everything within function not used at all for now
        else if (opcode.equals("deffunc")) {
            System.out.println("DEFINING FUNC: " + parts[1]);
            functionDefinitions.put(parts[1], currentLineNum);
            runnable = false;
        }

        //allows to execute noramally
        else if (opcode.equals("endfunc")) {
            runnable = true;
        }

        //end multiline comment
        else if (opcode.endsWith("*/")) {
            runnable = true;
        }

        //ouput var states
        showVariables();
        //loop back next statement
        getNextLine();
    }

    //searches for loops, so line numbers of start and end statements easily accessible
    //less searching when running loop mulitple times
    public HashMap<Integer, Integer> precompileLoops() {
        System.out.println("precompiled loops");
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Stack<String> loopPoints = new Stack<String>();
        String firstWord = "";
        String[] words = {};
        String firstElement = "";
        String secondElement = "";
        Integer found = 0;

        for (Integer i = 0; i < allLines.size(); i++){
            words = decodeLine(i);
            firstWord = words[0];
            if (firstWord.equals("while") ){
                loopPoints.push("w"+i.toString());
            } else if(firstWord.equals("end")){
                loopPoints.push("e"+i.toString());
            }

            if (loopPoints.size() >= 2) {
                firstElement = loopPoints.pop();
                secondElement = loopPoints.pop();

                if (firstElement.charAt(0) == 'e' && secondElement.charAt(0) == 'w'){
                    firstElement = firstElement.substring(1);
                    secondElement = secondElement.substring(1);
                    map.put(Integer.valueOf(firstElement), Integer.valueOf(secondElement));
                } else {
                    loopPoints.push(secondElement);
                    loopPoints.push(firstElement);
                }
            }
        }
        //put line nums of whiles and ends in
        //when a while and end are next to eachother
        //pop both and put into pair
        return map;
    }

    public <K, V> K getKeyForValue(Map<K, V> mapOfWords, V value)
    {
        //Check if Map contains the given value
        if(mapOfWords.containsValue(value))
        {

            // Iterate over each entry of map using entrySet
            for (Map.Entry<K, V> entry : mapOfWords.entrySet())
            {
                // Check if value matches with given value
                if (entry.getValue().equals(value))
                {
                    // Store the key from entry to the list
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    public void getNextLine(){
        currentLineNum ++;
        execute();

    }

    public void skipToLine(Integer lineNum) {
        currentLineNum = lineNum;
        execute();
    }

    public void showHashMap(HashMap<Integer,Integer> example) {
        for (Integer name: example.keySet()){
            String key = name.toString();
            String value = example.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

    //decode statement
    private String[] decodeLine(Integer lineNum){
        String line = "";
        line = allLines.get(lineNum);
        line = line.replace(";","");
        line = line.trim();

        String[] parts = line.split(" ");
        return parts;
    }

    public void error(String errorStatement) {
        System.out.print("ERROR: ");
        System.out.println(errorStatement);
    }

    public void showVariables() {
        System.out.println("------------------------------------");
        for (String name: variables.keySet()){
            String key = name.toString();
            String value = variables.get(name).toString();
            System.out.print(key + " " + value + ", ");
        }
        System.out.println("");
        System.out.println("------------------------------------");
    }
}