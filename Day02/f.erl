-module(f).
%-compile(export_all).
-export([test/0, test_file/0, compute_file/1, compute_w_param/3, compute/1]).
-import(maps, [put/3,get/2]).
%uses myio:read_file/2 07-01-2020

test() ->
	[2,0,0,0,99] = compute([1,0,0,0,99]),
	[2,3,0,6,99] = compute([2,3,0,3,99]),
	[2,4,4,5,99,9801] = compute([2,4,4,5,99,0]),
	[30,1,1,4,2,5,6,0,99] = compute([1,1,1,4,99,5,6,0,99]),
	works.

test_file() -> 
	Answer = compute_file("day02_data.txt"),
	3716250 = lists:nth(1, lists:nth(1, Answer)),
	works.

compute_file(Filename) -> compute_list(read_file(Filename)).

compute_list([]) -> [];
compute_list([P|L]) -> [compute(P)|compute_list(L)].

compute_w_param(A,B,[L|[]]) -> 
	M = put(2, B, put(1, A, list_to_map(L))),
	get(0, compute(M));
compute_w_param(A,B,Filename) -> compute_w_param(A,B,read_file(Filename)).

compute(L) when is_list(L) -> map_to_list(compute(list_to_map(L)));
compute(M) -> compute(M,0).
compute(M,P) -> case get(P,M) of
			99 -> M;
			1 -> compute(add(get(P+1,M), get(P+2,M), get(P+3,M), M), P+4);
			2 -> compute(multi(get(P+1,M), get(P+2,M), get(P+3,M), M), P+4)
		end.

add(A,B,R,M) -> put(R, get(A,M) + get(B,M), M).
multi(A,B,R,M) -> put(R, get(A,M) * get(B,M), M).

list_to_map([I|[]], N) -> #{N => I};
list_to_map([I|L], N) -> put(N, I, list_to_map(L,N+1)).
list_to_map(L) -> list_to_map(L,0).

map_to_list(M) -> lists:reverse(map_to_list(M,maps:size(M) - 1)).
map_to_list(M, 0) -> [get(0,M)];
map_to_list(M, P) -> [get(P,M) | map_to_list(M, P-1)].

read_file(Filename) -> myio:read_file(Filename, fun line_to_int_list/1).

line_to_int_list("")  -> [];
line_to_int_list(Str) -> 
	{I, R} = string:to_integer(string:trim(Str, leading, ",")),
	[I | line_to_int_list(R)].


