# coding=utf-8


if __name__ == '__main__':
    # import sys
    # import itertools
    #
    # number = int(sys.stdin.readline().strip())
    # alist = tuple(map(int, sys.stdin.readline().strip().split()))
    # res = list(itertools.permutations(range(1, number + 1), number))
    # number = res.index(alist)
    # res = list(res[-(number + 1)])
    # for i in res:
    #     print(i, end=' ')
    #    # import sys
    #
    # number = int(sys.stdin.readline().strip())
    # for i in range(number):
    #     n = int(sys.stdin.readline().strip())
    #     alist = list(map(int, sys.stdin.readline().strip().split()))
    #
    #     alist = sorted(alist)
    #     flag = True
    #     if alist[0] >= alist[1] + alist[-1]:
    #         flag = False
    #     elif alist[-1] > alist[-2] + alist[0]:
    #         flag = False
    #     else:
    #         for i in range(1, len(alist) - 1):
    #             if alist[i] > alist[i - 1] + alist[i + 1]:
    #                 flag = False
    #                 break
    #     print(flag)

    import sys

    import sys

    n = int(sys.stdin.readline().strip())
    for i in range(n):
        s = sys.stdin.readline().strip()
        t = sys.stdin.readline().strip()
        for i in range(len(t)):
            if t[i] == '0':
                t[i] = '1'
            else:
                t[i] = '0'
        if '0' not in str(int(t)):
            print('NO')
        else:
            print('YES')

