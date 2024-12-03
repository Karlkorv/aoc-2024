use regex::Regex;
use std::{
    fs::File,
    io::{BufRead, BufReader},
};

fn main() {
    let v: Vec<String> = read_input("input.txt");
    let parsed_input: Vec<String> = parse_input(&v);
    let result: i32 = solve(&parsed_input);
    print!("{}", result);
}

fn solve(parsed_input: &Vec<String>) -> i32 {
    let mut result: i32 = 0;
    let regex_match = Regex::new(r"mul\((\d+),(\d+)\)").unwrap();
    let mut enabled: bool = true;
    for input in parsed_input {
        match input.as_str() {
            "do()" => {
                enabled = true;
            }
            "don't()" => {
                enabled = false;
            }
            _ => {}
        }

        if !enabled {
            continue;
        }

        for cap in regex_match.captures_iter(&input) {
            let a: i32 = cap[1].parse().unwrap();
            let b: i32 = cap[2].parse().unwrap();
            result += a * b;
        }
    }

    result
}

fn parse_input(v: &Vec<String>) -> Vec<String> {
    let regex_match = Regex::new(r"(mul\(\d+,\d+\)|don't\(\)|do\(\))").unwrap();
    let mut parsed_input: Vec<String> = Vec::new();
    for line in v {
        let mut temp: Vec<String> = Vec::new();
        for cap in regex_match.captures_iter(&line) {
            temp.push(cap[1].to_string());
        }
        parsed_input.append(&mut temp);
    }

    return parsed_input;
}

fn read_input(arg: &str) -> Vec<String> {
    let mut v: Vec<String> = Vec::new();
    let file = File::open(arg).expect("file not found");
    let reader = BufReader::new(file);
    for line in reader.lines() {
        match line {
            Ok(l) => v.push(l),
            Err(e) => println!("{}", e),
        }
    }
    return v;
}
