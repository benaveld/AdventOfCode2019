-module(intcodeProcessor).
%-compile(export_all).
-export([test/0, test_file/0, compute_file/1, compute/2, compute/3]).
-import(mapUtil, [setValue/4, getValue/3, getModes/2]).

test() ->
	[2,0,0,0,99] = compute([1,0,0,0,99]),
	[7,2,5,0,99] = compute([1101, 2, 5, 0,99]),
	[2,3,0,6,99] = compute([2,3,0,3,99]),
	[2,4,4,5,99,9801] = compute([2,4,4,5,99,0]),
	[30,1,1,4,2,5,6,0,99] = compute([1,1,1,4,99,5,6,0,99]),
	%[1, 0, 99] = compute([3,0,99]),
	works.

test_file() -> 
	compute_file("day07_data.txt"),
	works.

compute_file(Filename) -> compute_list(mapUtil:read_file(Filename)).

compute_list([]) -> [];
compute_list([P|L]) -> [mapUtil:map_to_list(compute(P)) | compute_list(L)].

compute(L, IO) when is_list(L) ->{P,M} = compute(mapUtil:list_to_map(L), IO),
				 {P,mapUtil:map_to_list(M)};
compute(M, IO) when is_map(M) -> compute(M,0,IO).

compute(M,P,IO) -> case opcode(maps:get(P,M) rem 100, P, M, IO) of
			eof -> {P,M};
			{Pos, Map} -> compute(Map, Pos, IO)
		end.

%Opcodes--------------------------------------------------------
%add
opcode(1,Pos, M, _) -> 
	[A,B,C] = getModes(maps:get(Pos, M), 3),
	R = getValue(C, Pos+1, M) + getValue(B, Pos+2, M),
	{Pos+4, setValue(A, Pos+3, R, M)};

%multiply
opcode(2,Pos, M, _) ->
	[A,B,C] = getModes(maps:get(Pos,M), 3),
	R = getValue(C, Pos+1, M) * getValue(B, Pos+2, M),
	{Pos+4, setValue(A, Pos+3, R, M)};

%Input
opcode(3, Pos, M, {I,_O}) -> I(Pos,M);
	%[C] = getModes(maps:get(Pos,M), 1), 
	%{ok, [R]} = io:fread("Input : ", "~d"),
	%{Pos+2, setValue(C, Pos+1, R, M)};

%Output
opcode(4, Pos, M,{_I,O) -> O(Pos,M);
	%[C] = getModes(maps:get(Pos,M), 1),
	%io:format("Output: ~p~n", [getValue(C, Pos+1, M)]),
	%{Pos+2, M};

%Jump if true
opcode(5, Pos, M, _IO) ->
	[A,B] = getModes(maps:get(Pos,M), 2),
	case getValue(B, Pos+1, M) /= 0 of
		true -> {getValue(A, Pos+2, M), M};
		false -> {Pos+3, M}
	end;

%Jump if false
opcode(6, Pos, M, _IO) ->
	[A,B] = getModes(maps:get(Pos,M), 2),
	case getValue(B, Pos+1, M) == 0 of
		true -> {getValue(A, Pos+2, M), M};
		false -> {Pos+3, M}
	end;

%less then
opcode(7, Pos, M, _IO) ->
	[A,B,C] = getModes(maps:get(Pos,M), 3),
	case getValue(C, Pos+1, M) < getValue(B, Pos+2, M) of
		true -> {Pos+4, setValue(A, Pos+3, 1, M)};
		false -> {Pos+4, setValue(A, Pos+3, 0, M)}
	end;

%equals
opcode(8, Pos, M, _IO) ->
	[A,B,C] = getModes(maps:get(Pos,M), 3),
	case getValue(C, Pos+1, M) ==  getValue(B, Pos+2, M) of
		true -> {Pos+4, setValue(A, Pos+3, 1, M)};
		false -> {Pos+4, setValue(A, Pos+3, 0, M)}
	end;

%exit program
opcode(99, _Pos, _M, _IO) -> eof.
