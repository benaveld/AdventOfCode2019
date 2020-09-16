-module(e).
-compile(export_all).
-import(intcodeProcessor, [compute/2]).

amp([F|R], Program) -> compute(Program, 
