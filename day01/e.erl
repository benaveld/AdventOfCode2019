-module(e).
-compile(export_all).

test() -> 
	2 = fuel(12),
	2 = fuel(14),
	654 = fuel(1969),
	33583 = fuel(100756),
	works.

run() ->
	Lines = read_file("day01_data.txt"),
	Nrs = lines_to_int(Lines),
	sum(Nrs).

fuel(N) -> 
	F = trunc(math:floor(N / 3)) - 2,
	if F > 0 -> F + fuel(F);
	   true -> 0
	end.

sum([N|[]]) -> fuel(N);
sum([N|L]) -> fuel(N) + sum(L);
sum(N) -> fuel(N).

lines_to_int([L|[]])-> list_to_integer(L);
lines_to_int([L|Lines]) -> [list_to_integer(L)|lines_to_int(Lines)].

read_file(Filename) ->
	{ok, File} = file:open(Filename,[read]),
	try get_all_lines(File)
	after file:close(File)
	end.

get_all_lines(File) ->
	case io:get_line(File, "") of
		eof -> [];
		Line -> [string:trim(Line,trailing, "\n")|get_all_lines(File)]
	end.
