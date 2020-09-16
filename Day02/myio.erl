-module(myio).
-export([read_file/1, read_file/2]).

read_file(Filename) ->
	{ok, Device} = file:open(Filename,[read]),
	try get_all_lines(Device)
	after file:close(Device)
	end.

read_file(Filename, Mod) -> 
	{ok, Device} = file:open(Filename,[read]),
	try get_all_lines(Device, Mod)
	after file:close(Device)
	end.

get_all_lines(Device) -> 
	case io:get_line(Device, "") of
		eof -> [];
		Line -> [string:trim(Line, trailing, "\n") | get_all_lines(Device)]
	end.

get_all_lines(Device, Mod) -> 
	case io:get_line(Device, "") of
		eof -> [];
		Line -> [Mod(string:trim(Line, trailing, "\n")) | get_all_lines(Device)]
	end.

