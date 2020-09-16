-module(e).
-compile(export_all).
-import(lists, [nth/2]).

test()->
	[2,0,0,0,99] = compute([1,0,0,0,99]),
	[2,3,0,6,99] = compute([2,3,0,3,99]),
	[2,4,4,5,99,9801] = compute([2,4,4,5,99,0]),
	[30,1,1,4,2,5,6,0,99] = compute([1,1,1,4,99,5,6,0,99]),
	works.

compute(L) -> compute(L,1).
compute(L, P) when P =< length(L) -> 
       case nth(P,L) of
	       99 -> L;
	       1 -> compute(add(nthnth(P+1,L), nthnth(P+2,L), 1+nth(P+3,L),L), P+4);
	       2 -> compute(mult(nthnth(P+1,L), nthnth(P+2,L), 1+nth(P+3,L),L), P+4)
       end.

add(A,B,R,L) -> insert(A+B,R,L).
mult(A,B,R,L) -> insert(A*B,R,L).

insert(V, P, L) -> lists:sublist(L, P-1)++[V]++lists:nthtail(P,L).
nthnth(N,L) -> nth(nth(N,L)+1, L).
