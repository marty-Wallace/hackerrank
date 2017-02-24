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
    let mut s = get_input_line();
    let line = tokenize(&mut s);

    let n = line[0].parse::<usize>().unwrap();
    let k = line[1].parse::<usize>().unwrap();
    let q = line[2].parse::<usize>().unwrap();

    let mut s = get_input_line();
    let line = tokenize(&mut s);

    let mut nums = vec![0; n];
    let mut i = 0;

    // read in the numbers
    for token in line {
        let num = token.trim().parse::<u32>().unwrap();
        nums[i] = num;
        i += 1;
    }

    /*
    // we could also perform the rotations 
    // instead of modular arithmetic like so

    for _ in 0..k {
        let num = nums.remove(n-1);
        nums.insert(0, num);
    }
    */

    // print out the numbers
    for _ in 0..q {
        let query = get_input_line().trim().parse::<usize>().unwrap();

        // use modular arithmetic to figure out index after rotations
        println!("{}", nums[(query + n - k) % n]);
    }

}
