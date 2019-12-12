# Bare Bones Interpreter _Extended_

### The Language
This interpreter is an addition to the [previous challenge](https://github.com/JakubDylag/SpaceCadets/tree/master/2-BareBones).
Implements Subroutines and comments

#### Syntax
`deffunc` - defines subroutines with a certain name

`endfunc` - ends subroutines of function

`run` - runs the subroutines specificed

`/*` - starts a multi-line comment

`*/` - ends a multi-line comment 

`//` - starts a single line comment

#### Simple Function call Example
```text
deffunc add;
   incr X;
endfunc;
clear X;
clear Y;
clear Z;
run add;
incr X;
```
tests defining and running the function, as well as use of call stack and returning to line where it was called from

###### Output
```text
C:\Users\jakub\Documents\GitHub\SpaceCadets\3-BareBonesExt>java BareBones
Enter File Path:
sf.txt
precompiled loops
Current Line: deffunc add;
DEFINING FUNC: add
------------------------------------

------------------------------------
Current Line:    incr X;
------------------------------------

------------------------------------
Current Line: endfunc;
------------------------------------

------------------------------------
Current Line: clear X;
------------------------------------
X 0,
------------------------------------
Current Line: clear Y;
------------------------------------
X 0, Y 0,
------------------------------------
Current Line: clear Z;
------------------------------------
X 0, Y 0, Z 0,
------------------------------------
Current Line: run add;
Current Line:    incr X;
------------------------------------
X 1, Y 0, Z 0,
------------------------------------
Current Line: endfunc;
go back to: 7
Current Line: incr X;
------------------------------------
X 2, Y 0, Z 0,
------------------------------------
END
```

#### Functions with Comments Example
```text
deffunc add;
   incr X;
endfunc;
/*
this
is
a
multi-
line
comment
*/
clear X;
incr X;
incr X;
clear Y;
incr Y;
incr Y;
incr Y;
clear Z;
run add;
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
tests if comments are not executed
and while loops with subroutines calls

###### Output
```text
C:\Users\jakub\Documents\GitHub\SpaceCadets\3-BareBonesExt>java BareBones
Enter File Path:
func.txt
precompiled loops
32 20
26 22
30 27
Current Line: deffunc add;
DEFINING FUNC: add
------------------------------------

------------------------------------
Current Line:    incr X;
------------------------------------

------------------------------------
Current Line: endfunc;
------------------------------------

------------------------------------
Current Line: /*
------------------------------------

------------------------------------
Current Line: this
------------------------------------

------------------------------------
Current Line: is
------------------------------------

------------------------------------
Current Line: a
------------------------------------

------------------------------------
Current Line: multi-
------------------------------------

------------------------------------
Current Line: line
------------------------------------

------------------------------------
Current Line: comment
------------------------------------

------------------------------------
Current Line: */
------------------------------------

------------------------------------
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
Current Line: run add;
Current Line:    incr X;
------------------------------------
X 3, Y 3, Z 0,
------------------------------------
Current Line: endfunc;
go back to: 20
Current Line: while X not 0 do;
RAN while
------------------------------------
X 3, Y 3, Z 0,
------------------------------------
Current Line:    clear W;
------------------------------------
W 0, X 3, Y 3, Z 0,
------------------------------------
Current Line:    while Y not 0 do;
RAN while
------------------------------------
W 0, X 3, Y 3, Z 0,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 0, X 3, Y 3, Z 1,
------------------------------------
Current Line:       incr W;
------------------------------------
W 1, X 3, Y 3, Z 1,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 1, X 3, Y 2, Z 1,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while Y not 0 do;
RAN while
------------------------------------
W 1, X 3, Y 2, Z 1,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 1, X 3, Y 2, Z 2,
------------------------------------
Current Line:       incr W;
------------------------------------
W 2, X 3, Y 2, Z 2,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 2, X 3, Y 1, Z 2,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while Y not 0 do;
RAN while
------------------------------------
W 2, X 3, Y 1, Z 2,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 2, X 3, Y 1, Z 3,
------------------------------------
Current Line:       incr W;
------------------------------------
W 3, X 3, Y 1, Z 3,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 3, X 3, Y 0, Z 3,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while Y not 0 do;
RAN while
Current Line:    while W not 0 do;
RAN while
------------------------------------
W 3, X 3, Y 0, Z 3,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 3, X 3, Y 1, Z 3,
------------------------------------
Current Line:       decr W;
------------------------------------
W 2, X 3, Y 1, Z 3,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while W not 0 do;
RAN while
------------------------------------
W 2, X 3, Y 1, Z 3,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 2, X 3, Y 2, Z 3,
------------------------------------
Current Line:       decr W;
------------------------------------
W 1, X 3, Y 2, Z 3,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while W not 0 do;
RAN while
------------------------------------
W 1, X 3, Y 2, Z 3,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 1, X 3, Y 3, Z 3,
------------------------------------
Current Line:       decr W;
------------------------------------
W 0, X 3, Y 3, Z 3,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while W not 0 do;
RAN while
Current Line:    decr X;
------------------------------------
W 0, X 2, Y 3, Z 3,
------------------------------------
Current Line: end;
RAN END
Current Line: while X not 0 do;
RAN while
------------------------------------
W 0, X 2, Y 3, Z 3,
------------------------------------
Current Line:    clear W;
------------------------------------
W 0, X 2, Y 3, Z 3,
------------------------------------
Current Line:    while Y not 0 do;
RAN while
------------------------------------
W 0, X 2, Y 3, Z 3,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 0, X 2, Y 3, Z 4,
------------------------------------
Current Line:       incr W;
------------------------------------
W 1, X 2, Y 3, Z 4,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 1, X 2, Y 2, Z 4,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while Y not 0 do;
RAN while
------------------------------------
W 1, X 2, Y 2, Z 4,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 1, X 2, Y 2, Z 5,
------------------------------------
Current Line:       incr W;
------------------------------------
W 2, X 2, Y 2, Z 5,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 2, X 2, Y 1, Z 5,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while Y not 0 do;
RAN while
------------------------------------
W 2, X 2, Y 1, Z 5,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 2, X 2, Y 1, Z 6,
------------------------------------
Current Line:       incr W;
------------------------------------
W 3, X 2, Y 1, Z 6,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 3, X 2, Y 0, Z 6,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while Y not 0 do;
RAN while
Current Line:    while W not 0 do;
RAN while
------------------------------------
W 3, X 2, Y 0, Z 6,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 3, X 2, Y 1, Z 6,
------------------------------------
Current Line:       decr W;
------------------------------------
W 2, X 2, Y 1, Z 6,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while W not 0 do;
RAN while
------------------------------------
W 2, X 2, Y 1, Z 6,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 2, X 2, Y 2, Z 6,
------------------------------------
Current Line:       decr W;
------------------------------------
W 1, X 2, Y 2, Z 6,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while W not 0 do;
RAN while
------------------------------------
W 1, X 2, Y 2, Z 6,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 1, X 2, Y 3, Z 6,
------------------------------------
Current Line:       decr W;
------------------------------------
W 0, X 2, Y 3, Z 6,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while W not 0 do;
RAN while
Current Line:    decr X;
------------------------------------
W 0, X 1, Y 3, Z 6,
------------------------------------
Current Line: end;
RAN END
Current Line: while X not 0 do;
RAN while
------------------------------------
W 0, X 1, Y 3, Z 6,
------------------------------------
Current Line:    clear W;
------------------------------------
W 0, X 1, Y 3, Z 6,
------------------------------------
Current Line:    while Y not 0 do;
RAN while
------------------------------------
W 0, X 1, Y 3, Z 6,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 0, X 1, Y 3, Z 7,
------------------------------------
Current Line:       incr W;
------------------------------------
W 1, X 1, Y 3, Z 7,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 1, X 1, Y 2, Z 7,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while Y not 0 do;
RAN while
------------------------------------
W 1, X 1, Y 2, Z 7,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 1, X 1, Y 2, Z 8,
------------------------------------
Current Line:       incr W;
------------------------------------
W 2, X 1, Y 2, Z 8,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 2, X 1, Y 1, Z 8,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while Y not 0 do;
RAN while
------------------------------------
W 2, X 1, Y 1, Z 8,
------------------------------------
Current Line:       incr Z;
------------------------------------
W 2, X 1, Y 1, Z 9,
------------------------------------
Current Line:       incr W;
------------------------------------
W 3, X 1, Y 1, Z 9,
------------------------------------
Current Line:       decr Y;
------------------------------------
W 3, X 1, Y 0, Z 9,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while Y not 0 do;
RAN while
Current Line:    while W not 0 do;
RAN while
------------------------------------
W 3, X 1, Y 0, Z 9,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 3, X 1, Y 1, Z 9,
------------------------------------
Current Line:       decr W;
------------------------------------
W 2, X 1, Y 1, Z 9,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while W not 0 do;
RAN while
------------------------------------
W 2, X 1, Y 1, Z 9,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 2, X 1, Y 2, Z 9,
------------------------------------
Current Line:       decr W;
------------------------------------
W 1, X 1, Y 2, Z 9,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while W not 0 do;
RAN while
------------------------------------
W 1, X 1, Y 2, Z 9,
------------------------------------
Current Line:       incr Y;
------------------------------------
W 1, X 1, Y 3, Z 9,
------------------------------------
Current Line:       decr W;
------------------------------------
W 0, X 1, Y 3, Z 9,
------------------------------------
Current Line:    end;
RAN END
Current Line:    while W not 0 do;
RAN while
Current Line:    decr X;
------------------------------------
W 0, X 0, Y 3, Z 9,
------------------------------------
Current Line: end;
RAN END
Current Line: while X not 0 do;
RAN while
END
```