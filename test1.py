# coding=utf-8

if __name__ == '__main__':

    # 大疆
    import sys
    res = []
    while True:
        n_num = sys.stdin.readline().strip()
        if not n_num:
            break
        n_num = list(map(int,n_num.split()))
        n_list = list(map(int,sys.stdin.readline().strip().split()))

        allnum = sum(n_list)
        if allnum%n_num[1] == 0:
            need = allnum//n_num[1]
        else:
            need = (allnum//n_num[1])+1
        if n_num[2]*60 > need and need<=480:
            res.append(need)
        elif allnum-n_num[2] <=480:
            res.append(allnum-n_num[2])
        else:
            res.append(0)
    for i in res:
        print(i)



