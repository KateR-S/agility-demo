(ns agility-demo.utils)

(defn retrieve-class-details
  [db]
  (let [class (get-in db [:active :class])
        classes (:classes db)]
    (->> classes
         (filter #(= class (:Class %)))
         (first))))

(defn retrieve-user-details
  [db]
  (let [handler  (int (get-in db [:active :handler]))
        handlers (:users db)]
    (->> handlers
         (filter #(= handler (:id %)))
         (first))))

(defn class->header-string
  [{:keys [Class Size Type Grades]}]
  (str "Ring 1: " Class " " Size " " Type " " Grades))
