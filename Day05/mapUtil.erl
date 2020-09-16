-module(mapUtil).
%-compile(export_all).
-export([getValue/3, setValue/4, getModes/2, read_file/1, list_to_map/1, map_to_list/1]).
-import(maps, [put/3, get/2]).

getValue(0, Pos, M) -> get(get(Pos, M), M);
getValue(1, Pos, M) -> get(Pos, M).

setValue(0, Pos, Value, M) -> put(get(Pos, M), Value, M).

getModes(_V, 0) -> [];
getModes(V, I) when I > 0 -> [getDiget(V, I+1)|getModes(V,I-1)]. 

read_file(Filename) -> myio:read_file(Filename, fun line_to_int_map/1).

list_to_map([I|[]], N) -> #{N => I};
list_to_map([I|L], N) -> put(N, I, list_to_map(L,N+1)).
list_to_map(L) -> list_to_map(L,0).

map_to_list(M) -> lists:reverse(map_to_list(M,maps:size(M) - 1)).
map_to_list(M, 0) -> [get(0,M)];
map_to_list(M, P) -> [get(P,M) | map_to_list(M, P-1)].

%line_to_int_list("")  -> [];
%line_to_int_list(Str) -> 
%		{I, R} = string:to_integer(string:trim(Str, leading, ",")),
%			[I | line_to_int_list(R)].

line_to_int_map(Str) -> line_to_int_map(Str, 0).
line_to_int_map("", _P) -> maps:new();
line_to_int_map(Str, P) -> 
	{I, R} = string:to_integer(string:trim(Str, leading, ",")),
	put(P, I, line_to_int_map(R, P+1)).

getDiget(Input, 0) -> Input rem pow(10, 1);
getDiget(Input, N) when N > 0 -> trunc((Input rem pow(10, N+1)) / pow(10, N)).
pow(Base, Ex) -> trunc(math:pow(Base, Ex)).
