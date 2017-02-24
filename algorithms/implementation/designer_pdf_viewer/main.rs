
fn get_input_line() -> String {
    let mut buffer = String::new();
    std::io::stdin().read_line(&mut buffer).expect("Failed to read from stdin");
    buffer
}

fn tokenize(s: &mut String) -> Vec<&str> {
    let split: Vec<&str> = s.split_whitespace().collect();
    split
}


fn main() {
    let mut line = get_input_line();
    //takes over the scope of line
    let line = tokenize(&mut line);

    let mut nums = vec![0; 26];
    let mut i = 0;

    // read in the numbers
    for token in line {
        let num = token.trim().parse::<u32>().unwrap();
        nums[i] = num;
        i += 1;
    }

    let line = get_input_line();

    let mut max = 0;
    for c in line.chars() {
        if c == '\n' {
            continue;
        }

        //subtract a to get index in vector
        let temp: u32 = (c as u32) - ('a' as u32);


        let value = nums[temp as usize];
        if value > max {
            max = value
        }

    }

    let area = max * line.len() as u32;
    println!("{}", area);

}

