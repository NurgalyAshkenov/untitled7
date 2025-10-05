import pandas as pd
import matplotlib.pyplot as plt

# Укажите путь к файлу CSV с результатами
csv_file_path = 'docs/benchmark_results.csv'

# Загрузим CSV файл с результатами
df = pd.read_csv(csv_file_path)

# Построим график времени vs размер массива
plt.figure(figsize=(10, 6))
plt.plot(df['Array Size'], df['Time (ms)'], marker='o', linestyle='-', color='b')

# Настройка графика
plt.title('Performance Benchmark: Time vs Array Size')
plt.xlabel('Array Size')
plt.ylabel('Time (ms)')
plt.grid(True)
plt.xscale('log')  # Логарифмическая шкала для оси X (размер массива)
plt.yscale('log')  # Логарифмическая шкала для оси Y (время)

# Покажем график
plt.tight_layout()

# Путь для сохранения графика
plot_file_path = 'docs/performance_plot.png'
plt.savefig(plot_file_path)  # Сохраним график в папке docs
plt.show()

print(f"График сохранен в: {plot_file_path}")
