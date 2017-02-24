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
    let line = get_input_line();
    let t: u32 = line.trim().parse().unwrap();

    for _ in 0..t {
        let mut line = get_input_line();
        let stats = tokenize(&mut line);
        let n : usize = stats[0].parse().unwrap();
        let m : usize = stats[1].parse().unwrap();
        let max: u32 = stats[2].parse().unwrap();

        let mut stack1 = vec![0; n];
        let mut stack2 = vec![0; m];

        let mut line = get_input_line();
        let line1 = tokenize(&mut line);
        let mut last = 0_u32;
        let mut current;
        let mut best = 0_usize;

        for i in 0..n {
            current = line1[i].parse().unwrap();
            last += current;
            stack1[i] = last;
            if last <= max {
                best = i;
            }
        }

        let mut line = get_input_line();
        let line2 = tokenize(&mut line);
        last = 0_u32;

        for i in 0..m {
            current = line2[i].parse().unwrap();
            last += current;
            stack2[i] = last;
            if last <= max && i > best {
                best = i;
            }
        }


        for i in 0..stack1.len() {
            if stack1[i] > max { break;}
            for j in 0..stack2.len() {
                if stack1[i] + stack2[j] > max {
                    break;
                }
                if i+1 + j+1 > best{
                    best = i+1 + j+1;
                }
            }
        }

        println!("{}", best);
    }
}


