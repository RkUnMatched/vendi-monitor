import threading
import time

class AlternatingPrinter:
    def __init__(self, max_count=10):
        self.max_count = max_count
        self.current_number = 1
        self.lock = threading.Lock()
        self.odd_event = threading.Event()
        self.even_event = threading.Event()
        # 设置初始状态：奇数线程先开始
        self.odd_event.set()
        
    def odd_printer(self):
        """奇数线程打印函数"""
        for i in range(self.max_count):
            # 等待轮到奇数线程
            self.odd_event.wait()
            
            with self.lock:
                print(f"奇数线程: {self.current_number}")
                self.current_number += 1
                
                # 切换到偶数线程
                self.odd_event.clear()
                self.even_event.set()
    
    def even_printer(self):
        """偶数线程打印函数"""
        for i in range(self.max_count):
            # 等待轮到偶数线程
            self.even_event.wait()
            
            with self.lock:
                print(f"偶数线程: {self.current_number}")
                self.current_number += 1
                
                # 切换到奇数线程
                self.even_event.clear()
                self.odd_event.set()
    
    def run(self):
        """运行交替打印"""
        print("开始交替打印...")
        
        # 创建线程
        odd_thread = threading.Thread(target=self.odd_printer, name="奇数线程")
        even_thread = threading.Thread(target=self.even_printer, name="偶数线程")
        
        # 启动线程
        odd_thread.start()
        even_thread.start()
        
        # 等待线程完成
        odd_thread.join()
        even_thread.join()
        
        print("交替打印完成！")

if __name__ == "__main__":
    printer = AlternatingPrinter(10)
    printer.run()