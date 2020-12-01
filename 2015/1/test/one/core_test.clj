(ns one.core-test
  (:require [clojure.test :refer :all]
            [one.core :refer :all]))

(deftest examples-part-one
  (testing "Examples for part one."
    (is (= 0  (one.core/traverse-floors "(())")))
    (is (= 0  (one.core/traverse-floors "()()")))
    (is (= 3  (one.core/traverse-floors "(((")))
    (is (= 3  (one.core/traverse-floors "(()(()(")))
    (is (= 3  (one.core/traverse-floors "))(((((")))
    (is (= -1 (one.core/traverse-floors "())")))
    (is (= -1 (one.core/traverse-floors "))(")))
    (is (= -3 (one.core/traverse-floors ")())())")))))

(deftest examples-part-two
  (testing "Examples for part two."
    (is (= 1 (one.core/first-basement ")")))
    (is (= 5 (one.core/first-basement "()())")))))
