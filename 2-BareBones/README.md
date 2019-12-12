# Bare Bones Interpreter

###The Language
The interpreter is based of the [Bare Bones Language](http://www.brouhaha.com/~eric/software/barebones/bare_bones_language_summary.html).
However some changes to the syntax have been made.

#### Syntax
`clear` - **initializes** a variable, has to be done before referencing variable anywhere in program

`incr` - increments variable value by one

`decr` - decreases variable value by one

`while X not 0 do` - defines a **while loop**, which 

`end` - indicates end of **while loop**

#### Variables Example
```text
clear name;
clear bob;
incr name;
incr bob;
decr name;
```
###### Output
```text
C:\Users\jakub\Documents\GitHub\SpaceCadets\2-BareBones>java BareBones
Enter File Path:
vars.txt
precompiled
Current Line: clear name;
------------------------------------
name 0,
------------------------------------
Current Line: clear bob;
------------------------------------
bob 0, name 0,
------------------------------------
Current Line: incr name;
------------------------------------
bob 0, name 1,
------------------------------------
Current Line: incr bob;
------------------------------------
bob 1, name 1,
------------------------------------
Current Line: decr name;
------------------------------------
bob 1, name 0,
------------------------------------
END
```

#### Nested While Loops Example
```text
clear X;
incr X;
incr X;
clear Y;
incr Y;
incr Y;
incr Y;
clear Z;
while X not 0 do;
   clear W;
   while Y not 0 do;
      incr Z;
      incr W;
      decr Y;
   end;
   while W not 0 do;
      incr Y;
      decr W;
   end;
   decr X;
end;
```

######Output
```text
C:\Users\jakub\Documents\GitHub\SpaceCadets\2-BareBones>java BareBones
Enter File Path:
complex.txt
precompiled
while found at 8
while found at 10
end found at 14
14, 10
while found at 15
end found at 18
18, 15
end found at 20
20, 8
18 15
20 8
14 10
Current Line: clear X;
------------------------------------
X 0,
------------------------------------
Current Line: incr X;
------------------------------------
X 1,
------------------------------------
Current Line: incr X;
------------------------------------
X 2,
------------------------------------
Current Line: clear Y;
------------------------------------
X 2, Y 0,
------------------------------------
Current Line: incr Y;
------------------------------------
X 2, Y 1,
------------------------------------
Current Line: incr Y;
------------------------------------
X 2, Y 2,
------------------------------------
Current Line: incr Y;
------------------------------------
X 2, Y 3,
------------------------------------
Current Line: clear Z;
------------------------------------
X 2, Y 3, Z 0,
------------------------------------
RAN while
Current Line: while X not 0 do;
------------------------------------
X 2, Y 3, Z 0,
------------------------------------
Current Line:    clear W;
------------------------------------
W 0, X 2, Y 3, Z 0,
------------------------------------
RAN while
Current Line:    while Y not 0 do;
------------------------------------
W 0, X 2, Y 3, Z 0,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 0, X 2, Y 3, Z 1,
------------------------------------
Current Line:       incr W;
------------------------------------
W 1, X 2, Y 3, Z 1,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 1, X 2, Y 2, Z 1,
------------------------------------
RAN END
RAN while
Current Line:    while Y not 0 do;
------------------------------------
W 1, X 2, Y 2, Z 1,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 1, X 2, Y 2, Z 2,
------------------------------------
Current Line:       incr W;
------------------------------------
W 2, X 2, Y 2, Z 2,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 2, X 2, Y 1, Z 2,
------------------------------------
RAN END
RAN while
Current Line:    while Y not 0 do;
------------------------------------
W 2, X 2, Y 1, Z 2,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 2, X 2, Y 1, Z 3,
------------------------------------
Current Line:       incr W;
------------------------------------
W 3, X 2, Y 1, Z 3,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 3, X 2, Y 0, Z 3,
------------------------------------
RAN END
RAN while
RAN while
Current Line:    while W not 0 do;
------------------------------------
W 3, X 2, Y 0, Z 3,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 3, X 2, Y 1, Z 3,
------------------------------------
Current Line:       decr W;
------------------------------------
W 2, X 2, Y 1, Z 3,
------------------------------------
RAN END
RAN while
Current Line:    while W not 0 do;
------------------------------------
W 2, X 2, Y 1, Z 3,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 2, X 2, Y 2, Z 3,
------------------------------------
Current Line:       decr W;
------------------------------------
W 1, X 2, Y 2, Z 3,
------------------------------------
RAN END
RAN while
Current Line:    while W not 0 do;
------------------------------------
W 1, X 2, Y 2, Z 3,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 1, X 2, Y 3, Z 3,
------------------------------------
Current Line:       decr W;
------------------------------------
W 0, X 2, Y 3, Z 3,
------------------------------------
RAN END
RAN while
Current Line:    decr X;
------------------------------------
W 0, X 1, Y 3, Z 3,
------------------------------------
RAN END
RAN while
Current Line: while X not 0 do;
------------------------------------
W 0, X 1, Y 3, Z 3,
------------------------------------
Current Line:    clear W;
------------------------------------
W 0, X 1, Y 3, Z 3,
------------------------------------
RAN while
Current Line:    while Y not 0 do;
------------------------------------
W 0, X 1, Y 3, Z 3,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 0, X 1, Y 3, Z 4,
------------------------------------
Current Line:       incr W;
------------------------------------
W 1, X 1, Y 3, Z 4,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 1, X 1, Y 2, Z 4,
------------------------------------
RAN END
RAN while
Current Line:    while Y not 0 do;
------------------------------------
W 1, X 1, Y 2, Z 4,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 1, X 1, Y 2, Z 5,
------------------------------------
Current Line:       incr W;
------------------------------------
W 2, X 1, Y 2, Z 5,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 2, X 1, Y 1, Z 5,
------------------------------------
RAN END
RAN while
Current Line:    while Y not 0 do;
------------------------------------
W 2, X 1, Y 1, Z 5,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 2, X 1, Y 1, Z 6,
------------------------------------
Current Line:       incr W;
------------------------------------
W 3, X 1, Y 1, Z 6,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 3, X 1, Y 0, Z 6,
------------------------------------
RAN END
RAN while
RAN while
Current Line:    while W not 0 do;
------------------------------------
W 3, X 1, Y 0, Z 6,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 3, X 1, Y 1, Z 6,
------------------------------------
Current Line:       decr W;
------------------------------------
W 2, X 1, Y 1, Z 6,
------------------------------------
RAN END
RAN while
Current Line:    while W not 0 do;
------------------------------------
W 2, X 1, Y 1, Z 6,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 2, X 1, Y 2, Z 6,
------------------------------------
Current Line:       decr W;
------------------------------------
W 1, X 1, Y 2, Z 6,
------------------------------------
RAN END
RAN while
Current Line:    while W not 0 do;
------------------------------------
W 1, X 1, Y 2, Z 6,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 1, X 1, Y 3, Z 6,
------------------------------------
Current Line:       decr W;
------------------------------------
W 0, X 1, Y 3, Z 6,
------------------------------------
RAN END
RAN while
Current Line:    decr X;
------------------------------------
W 0, X 0, Y 3, Z 6,
------------------------------------
RAN END
RAN while
END

```
